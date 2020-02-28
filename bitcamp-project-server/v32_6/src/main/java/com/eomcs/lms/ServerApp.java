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

  // 커맨드 디자인 패턴과 관련된 코드(ex: Servlet 구현체)를 담고 꺼낼 수 있도록 Map을 준비
  Map<String, Servlet> servletMap = new HashMap<>();

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

  @SuppressWarnings("unchecked")
  public void service() {
    notifyApplicationInitialized();

    lessons = (List<Lesson>) context.get("lessonList");
    members = (List<Member>) context.get("memberList");
    boards = (List<Board>) context.get("boardList");

    // 이전에는 switch문의 문자열을 받아 데이터를 처리하는 메서드를 호출하였다.
    // 이번에는 메서드들을 Servlet이라는 동일한 규칙을 따르는 각각의 클래스로 만들었다.
    // 커맨드 객체 역할을 수행하는 서블릿 객체를 맵에 보관한다.
    servletMap.put("/lesson/list", new LessonListServlet(lessons));
    servletMap.put("/lesson/add", new LessonAddServlet(lessons));
    servletMap.put("/lesson/detail", new LessonDetailServlet(lessons));
    servletMap.put("/lesson/update", new LessonUpdateServlet(lessons));
    servletMap.put("/lesson/delete", new LessonDeleteServlet(lessons));
    servletMap.put("/member/list", new MemberListServlet(members));
    servletMap.put("/member/add", new MemberAddServlet(members));
    servletMap.put("/member/detail", new MemberDetailServlet(members));
    servletMap.put("/member/update", new MemberUpdateServlet(members));
    servletMap.put("/member/delete", new MemberDeleteServlet(members));
    servletMap.put("/board/list", new BoardListServlet(boards));
    servletMap.put("/board/add", new BoardAddServlet(boards));
    servletMap.put("/board/detail", new BoardDetailServlet(boards));
    servletMap.put("/board/update", new BoardUpdateServlet(boards));
    servletMap.put("/board/delete", new BoardDeleteServlet(boards));

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
          // 메서드를 각각의 Servlet 클래스로 이동,
          // request를 key로 받아 객체를 Map으로 꺼내는 방법으로 변경!
          // case "/lesson/add": addLesson(in, out); break;
          // case "/lesson/list": listLesson(out); break;
          // case "/lesson/detail": detailLesson(in, out); break;
          // case "/lesson/update": updateLesson(in, out); break;
          // case "/lesson/delete": deleteLesson(in, out); break;
          // case "/member/add": addMember(in, out); break;
          // case "/member/list": listMember(out); break;
          // case "/member/detail": detailMember(in, out); break;
          // case "/member/update": updateMember(in, out); break;
          // case "/member/delete": deleteMember(in, out); break;
          // case "/board/add": addBoard(in, out); break;
          // case "/board/list": listBoard(out); break;
          // case "/board/detail": detailBoard(in, out); break;
          // case "/board/update": updateBoard(in, out); break;
          // case "/board/delete": deleteBoard(in, out); break;
          // default: notFound(out);
        }

        // 클라이언트의 요청을 처리할 객체를 찾는다.
        // 클라이언트로부터 응답을 받아 Map을 이용하여 해당하는 서블릿 클래스의 객체를 꺼내
        // 그 객체의 service() 메서드를 호출하게 하였다.
        Servlet servlet = servletMap.get(request);

        if (servlet != null) {
          // 클라이언트의 요청을 처리할 객체를 찾았으면 작업을 실행한다.
          try {
            servlet.service(in, out);
            // 준비한 입출력 스트림을 아규먼트로 넘겨 데이터를 처리하도록 한다.

          } catch (Exception e) {
            // 요청한 작업을 수행하다가 오류가 발생할 경우 그 이유를 클라이언트에게 간단히 응답한다.
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());

            // 서버쪽 화면에는 더 자세하게 오류 내용을 출력한다.
            System.out.println("클라이언트 요청 처리 중 오류 발생:");
            e.printStackTrace();
          }

        } else {
          // 클라이언트의 요청을 처리할 객체를 찾지 못했으면 간단한 안내 메시지를 응답한다.
          notFound(out);
        }

        out.flush(); // 성공 여부에 상관없이 flush() 호출하기
        // TCP/IP에 패킷이 꽉 차있지 않아도 내보냄

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

  // deleteLesson(ObjectInputStream, ObjectOutputStream) {} 삭제
  // updateLesson(ObjectInputStream, ObjectOutputStream) {} 삭제
  // detailLesson(ObjectInputStream, ObjectOutputStream) {} 삭제
  // addLesson(ObjectInputStream, ObjectOutputStream) {} 삭제
  // listLesson(ObjectOutputStream) {} 삭제
  // deleteMember(ObjectInputStream, ObjectOutputStream) {} 삭제
  // updateMember(ObjectInputStream, ObjectOutputStream) {} 삭제
  // detailMember(ObjectInputStream, ObjectOutputStream) {} 삭제
  // addMember(ObjectInputStream, ObjectOutputStream) {} 삭제
  // listMember(ObjectOutputStream) {} 삭제
  // deleteBoard(ObjectInputStream, ObjectOutputStream) {} 삭제
  // updateBoard(ObjectInputStream, ObjectOutputStream) {} 삭제
  // detailBoard(ObjectInputStream, ObjectOutputStream) {} 삭제
  // addBoard(ObjectInputStream, ObjectOutputStream) {} 삭제
  // listBoard(ObjectOutputStream) {} 삭제
}