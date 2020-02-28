// LMS 서버
package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.eomcs.context.ApplicationContextListener;
import com.eomcs.lms.dao.BoardObjectFileDao;
import com.eomcs.lms.dao.LessonObjectFileDao;
import com.eomcs.lms.dao.MemberObjectFileDao;
import com.eomcs.lms.servlet.BoardAddServlet;
import com.eomcs.lms.servlet.BoardDeleteServlet;
import com.eomcs.lms.servlet.BoardDetailServlet;
import com.eomcs.lms.servlet.BoardListServlet;
import com.eomcs.lms.servlet.BoardUpdateServlet;
import com.eomcs.lms.servlet.LessonAddServlet;
import com.eomcs.lms.servlet.LessonDeleteServlet;
import com.eomcs.lms.servlet.LessonDetailServlet;
import com.eomcs.lms.servlet.LessonListServlet;
import com.eomcs.lms.servlet.LessonUpdateServlet;
import com.eomcs.lms.servlet.MemberAddServlet;
import com.eomcs.lms.servlet.MemberDeleteServlet;
import com.eomcs.lms.servlet.MemberDetailServlet;
import com.eomcs.lms.servlet.MemberListServlet;
import com.eomcs.lms.servlet.MemberUpdateServlet;
import com.eomcs.lms.servlet.Servlet;

public class ServerApp {
  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();
  Map<String, Servlet> servletMap = new HashMap<>();

  // XxxObjectFileDao로 이동
  // List<Lesson> lessons;
  // List<Member> members;
  // List<Board> boards;

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
    // DataLoaderListener의 contextInitialized()를 호출
    // => 파일을 생성자 파라미터로 받은 XxxObjectFileDao 구현체가 생성됨
    // => 아래와 같이 XxxObjectFileDao 구현체를 context에서 꺼낼 수 있게 됨

    LessonObjectFileDao lessonDao = (LessonObjectFileDao) context.get("lessonDao");
    MemberObjectFileDao memberDao = (MemberObjectFileDao) context.get("memberDao");
    BoardObjectFileDao boardDao = (BoardObjectFileDao) context.get("boardDao");

    // 더이상 List 객체를 DataLoaderListener의 context Map에서 꺼내지 않고 XxxObjectfileDao가 준비
    // lessons = (List<Lesson>) context.get("lessonList");
    // members = (List<Member>) context.get("memberList");
    // boards = (List<Board>) context.get("boardList");

    // Servlet 구현체의 생성자 파라미터를 List 객체 lessons, members, boards가 아닌
    // XxxObjectFileDao 구현체 lessonDao, memberDao, boardDao를 넘겨주도록 변경
    servletMap.put("/lesson/list", new LessonListServlet(lessonDao));
    servletMap.put("/lesson/add", new LessonAddServlet(lessonDao));
    servletMap.put("/lesson/detail", new LessonDetailServlet(lessonDao));
    servletMap.put("/lesson/update", new LessonUpdateServlet(lessonDao));
    servletMap.put("/lesson/delete", new LessonDeleteServlet(lessonDao));
    servletMap.put("/member/list", new MemberListServlet(memberDao));
    servletMap.put("/member/add", new MemberAddServlet(memberDao));
    servletMap.put("/member/detail", new MemberDetailServlet(memberDao));
    servletMap.put("/member/update", new MemberUpdateServlet(memberDao));
    servletMap.put("/member/delete", new MemberDeleteServlet(memberDao));
    servletMap.put("/board/list", new BoardListServlet(boardDao));
    servletMap.put("/board/add", new BoardAddServlet(boardDao));
    servletMap.put("/board/detail", new BoardDetailServlet(boardDao));
    servletMap.put("/board/update", new BoardUpdateServlet(boardDao));
    servletMap.put("/board/delete", new BoardDeleteServlet(boardDao));

    try (ServerSocket serverSocket = new ServerSocket(9999)) {
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

  int processRequest(Socket clientSocket) {
    try(
        Socket socket = clientSocket;
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
      System.out.println("통신을 위한 입출력 스트림을 준비하였음!");

      while (true) {
        // 먼저 클라이언트의 Command.execute()가 호출될 때 서버에게 보내는 커맨드를 받는다.
        String request = in.readUTF();
        System.out.println("클라이언트가 보낸 메시지: " + request);
        System.out.println("클라이언트가 보낸 메시지를 수신하였음!");

        switch (request) {
          case "quit": quit(out); return 0; // 클라이언트와 연결을 끊는다.
          case "/server/stop": quit(out); return 9; // 클라이언트와 연결을 끊고 서버를 종료한다.
        }

        Servlet servlet = servletMap.get(request);

        if (servlet != null) {
          try {
            servlet.service(in, out);

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());

            System.out.println("클라이언트 요청 처리 중 오류 발생:");
            e.printStackTrace();
          }

        } else {
          notFound(out);
        }

        out.flush();

        System.out.println("클라이언트에게 응답하였음!");
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

  public static void main(String[] args) {
    System.out.println("서버 수업 관리 시스템입니다.");

    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
  }
}