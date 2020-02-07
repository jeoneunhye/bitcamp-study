// LMS 서버
package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.eomcs.context.ApplicationContextListener;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;

public class ServerApp {
  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();

  // 각각 명령어 처리 메서드로 분리하기 위해 인스턴스 필드로 내보내기
  // 예외처리할 필요가 없기 때문에 try문 밖으로 나가도 된다.
  List<Lesson> lessons;
  List<Member> members;
  List<Board> boards;

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  public void notifyApplicationInitialized() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(context);
    }
  }

  public void notifyApplicationDestroyed() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(context);
    }
  }

  public void service() {
    notifyApplicationInitialized();

    // DataLoaderListener가 준비한 데이터를 꺼내 List를 인스턴스 필드에 저장한다.
    lessons = (List<Lesson>) context.get("lessonList");
    members = (List<Member>) context.get("memberList");
    boards = (List<Board>) context.get("boardList");

    try (
        ServerSocket serverSocket = new ServerSocket(9999)) {
      System.out.println("클라이언트 연결 대기중...");

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결되었음!");

        if (processRequest(socket) == 9) {
          break;
        }

        System.out.println("-----클라이언트 요청 처리 끝-----");
      }
    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생!");
    }

    notifyApplicationDestroyed();
  }

  @SuppressWarnings("unchecked")
  int processRequest(Socket clientSocket) {
    try(
        Socket socket = clientSocket;

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
      System.out.println("통신을 위한 입출력 스트림을 준비하였음!");

      while (true) {
        String request = in.readUTF();
        System.out.println("클라이언트가 보낸 메시지: " + request);
        System.out.println("클라이언트가 보낸 메시지를 수신하였음!");

        // 문자열을 받아 if문 실행 => switch문으로 리팩토링
        switch (request) {
          case "quit": quit(out); return 0; // 클라이언트와 연결을 끊는다.
          case "/server/stop": quit(out); return 9; // 클라이언트와 연결을 끊고 서버를 종료한다.
          case "/board/list": listBoard(out); break;
          case "/board/add": addBoard(in, out); break;
          case "/board/detail": detailBoard(in, out); break;
          case "/board/update": updateBoard(in, out); break;
          case "/board/delete": deleteBoard(in, out); break;
          case "/lesson/list": listLesson(out); break;
          case "/lesson/add": addLesson(in, out); break;
          case "/lesson/detail": detailLesson(in, out); break;
          case "/lesson/update": updateLesson(in, out); break;
          case "/lesson/delete": deleteLesson(in, out); break;
          case "/member/list": listMember(out); break;
          case "/member/add": addMember(in, out); break;
          case "/member/detail": detailMember(in, out); break;
          case "/member/update": updateMember(in, out); break;
          case "/member/delete": deleteMember(in, out); break;
          default: notFound(out);
        }

        /*
        if (request.equals("quit")) {
          quit(out);
          break;
        }

        if (request.equals("/server/stop")) {
          quit(out);
          return 9;
        }

        if (request.equals("/board/list")) {
          listBoard(out);
        } else if (request.equals("/board/add")) {
          addBoard(in, out);
        } else if (request.equals("/board/detail")) {
          detailBoard(in, out);
        } else if (request.equals("/board/update")) {
          updateBoard(in, out);
        } else if (request.equals("/board/delete")) {
          deleteBoard(in, out);
        } else if (request.equals("/member/list")) {
          listMember(out);
        } else if (request.equals("/member/add")) {
          addMember(in, out);
        } else if (request.equals("/member/detail")) {
          detailMember(in, out);
        } else if (request.equals("/member/update")) {
          updateMember(in, out);
        } else if (request.equals("/member/delete")) {
          deleteMember(in, out);
        } else if (request.equals("/lesson/list")) {
          listLesson(out);
        } else if (request.equals("/lesson/add")) {
          addLesson(in, out);
        } else if (request.equals("/lesson/detail")) {
          detailLesson(in, out);
        } else if (request.equals("/lesson/update")) {
          updateLesson(in, out);
        } else if (request.equals("/lesson/delete")) {
          deleteLesson(in, out);
        } else {
          notFound(out);
        }
         */

        out.flush();
        System.out.println("클라이언트에게 응답하였음!");
        // return 0;
      } // while문 끝

    } catch(Exception e) {
      System.out.print("예외 발생: ");
      e.printStackTrace();
      return -1;
    }
  }

  private void notFound(ObjectOutputStream out) throws IOException {
    out.writeUTF("FAIL");
    out.writeUTF("요청한 명령을 처리하지 못했습니다.");
  }

  private void quit(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.flush();
  }

  private void deleteLesson(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      int no = in.readInt();

      int index = -1;
      for (int i = 0; i < lessons.size(); i++) {
        if (lessons.get(i).getNo() == no) {
          index = i;
          break;
        }
      }

      if (index != -1) {
        lessons.remove(index);
        out.writeUTF("OK");

      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 수업이 없습니다.");
      }

    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void updateLesson(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      Lesson lesson = (Lesson) in.readObject();

      int index = -1;
      for (int i = 0; i < lessons.size(); i++) {
        if (lessons.get(i).getNo() == lesson.getNo()) {
          index = i;
          break;
        }
      }

      if (index != -1) {
        lessons.set(index, lesson);
        out.writeUTF("OK");

      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 수업이 없습니다.");
      }

    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void detailLesson(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      int no = in.readInt();

      Lesson lesson = null;
      for (Lesson l : lessons) {
        if (l.getNo() == no) {
          lesson = l;
          break;
        }
      }

      if (lesson != null) {
        out.writeUTF("OK");
        out.writeObject(lesson);

      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 수업이 없습니다.");
      }

    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void addLesson(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      Lesson lesson = (Lesson) in.readObject();

      int i = 0;
      for (; i < lessons.size(); i++) {
        if (lessons.get(i).getNo() == lesson.getNo()) {
          break;
        }
      }

      if (i == lessons.size()) {
        lessons.add(lesson);
        out.writeUTF("OK");

      } else {
        out.writeUTF("FAIL");
        out.writeUTF("같은 번호의 수업이 있습니다.");
      }

    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void listLesson(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(lessons);
  }

  private void deleteMember(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      int no = in.readInt();

      int index = -1;
      for (int i = 0; i < members.size(); i++) {
        if (members.get(i).getNo() == no) {
          index = i;
          break;
        }
      }

      if (index != -1) {
        members.remove(index);
        out.writeUTF("OK");

      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 회원이 없습니다.");
      }

    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void updateMember(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      Member member = (Member) in.readObject();

      int index = -1;
      for (int i = 0; i < members.size(); i++) {
        if (members.get(i).getNo() == member.getNo()) {
          index = i;
          break;
        }
      }

      if (index != -1) {
        members.set(index, member);
        out.writeUTF("OK");

      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 회원이 없습니다.");
      }

    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void detailMember(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      int no = in.readInt();

      Member member = null;
      for (Member m : members) {
        if (m.getNo() == no) {
          member = m;
          break;
        }
      }

      if (member != null) {
        out.writeUTF("OK");
        out.writeObject(member);

      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 회원이 없습니다.");
      }

    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void addMember(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      Member member = (Member) in.readObject();

      int i = 0;
      for (; i < members.size(); i++) {
        if (members.get(i).getNo() == member.getNo()) {
          break;
        }
      }

      if (i == members.size()) {
        members.add(member);
        out.writeUTF("OK");

      } else {
        out.writeUTF("FAIL");
        out.writeUTF("같은 번호의 회원이 있습니다.");
      }

    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void listMember(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(members);
  }

  private void deleteBoard(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      int no = in.readInt();

      int index = -1;

      for (int i = 0; i < boards.size(); i++) {
        if (boards.get(i).getNo() == no) {
          index = i;
          break;
        }
      }

      if (index == -1) {
        boards.remove(index);
        out.writeUTF("OK");

      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 게시물이 없습니다.");
      }

    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void updateBoard(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      Board board = (Board) in.readObject();

      int index = -1;
      for (int i = 0; i < boards.size(); i++) {
        if (boards.get(i).getNo() == board.getNo()) {
          index = i;
          break;
        }
      }

      if (index != -1) {
        boards.set(index, board);
        out.writeUTF("OK");

      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 게시물이 없습니다.");
      }

    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void detailBoard(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      int no = in.readInt();

      Board board = null;
      for (Board b : boards) {
        if (b.getNo() == no) {
          board = b;
          break;
        }
      }

      if (board != null) {
        out.writeUTF("OK");
        out.writeObject(board);

      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 게시물이 없습니다.");
      }

    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void addBoard(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      Board board = (Board) in.readObject();

      int i = 0;
      for (; i < boards.size(); i++) {
        if (boards.get(i).getNo() == board.getNo()) {
          break;
        }
      }

      if (i == boards.size()) {
        boards.add(board);
        System.out.println("게시물을 저장하였습니다.");
        out.writeUTF("OK");

      } else {
        out.writeUTF("FAIL");
        out.writeUTF("같은 번호의 게시물이 있습니다.");
      }

    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void listBoard(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(boards);
  }

  public static void main(String[] args) {
    System.out.println("서버 수업 관리 시스템입니다.");
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
  }
}