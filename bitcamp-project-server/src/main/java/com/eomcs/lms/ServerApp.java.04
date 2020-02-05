// LMS 서버
package com.eomcs.lms;

import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import com.eomcs.context.ApplicationContextListener;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;

public class ServerApp {
  // 옵저버 관련 코드
  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();

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

    List<Lesson> lessonList = (List<Lesson>) context.get("lessonList");
    List<Member> memberList = (List<Member>) context.get("memberList");
    List<Board> boardList = (List<Board>) context.get("boardList");

    notifyApplicationDestroyed();
  }

  public static void main(String[] args) {
    System.out.println("서버 수업 관리 시스템입니다.");
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
  }
  /*
      try (
          ServerSocket serverSocket = new ServerSocket(9999)) {
        System.out.println("클라이언트 연결 대기중...");

        while (true) {
          Socket socket = serverSocket.accept();
          System.out.println("클라이언트와 연결되었음!");

          processRequest(socket);

          System.out.println("-----클라이언트 요청 처리 끝-----");
        }

      } catch (Exception e) {
        System.out.println("서버 준비 중 오류 발생!");
        return;
      }
    }
   */

  static void processRequest(Socket clientSocket) {
    try(
        Socket socket = clientSocket;

        Scanner in = new Scanner(socket.getInputStream());

        PrintStream out = new PrintStream(socket.getOutputStream())
        ) {
      System.out.println("통신을 위한 입출력 스트림을 준비하였음!");

      String message = in.nextLine();
      System.out.println("클라이언트가 보낸 메시지: " + message);
      System.out.println("클라이언트가 보낸 메시지를 수신하였음!");

      out.print("어서오세요~");
      System.out.println("클라이언트로 메시지를 전송하였음!");

    } catch(Exception e) {
      System.out.println("예외 발생:");
      e.printStackTrace();
    }
  }
}