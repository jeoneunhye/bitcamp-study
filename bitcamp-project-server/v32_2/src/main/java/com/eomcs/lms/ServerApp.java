// LMS 서버
package com.eomcs.lms;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerApp {
  public static void main(String[] args) {
    System.out.println("서버 수업 관리 시스템입니다.");

    try (
        // 서버쪽 연결 준비
        // => 클라이언트와의 연결을 9999번 포트에서 기다린다.
        ServerSocket serverSocket = new ServerSocket(9999)) {
      System.out.println("클라이언트 연결 대기중...");

      while (true) { // 클라이언트와의 연결을 계속 받겠다.
        // 서버에 대기하고 있는, 클라이언트와 연결(승인)
        // => 대기하고 있는 클라이언트와 연결될 때까지 리턴하지 않는다!
        Socket socket = serverSocket.accept();
        // "환자분 진료실로 들어오세요."
        System.out.println("클라이언트와 연결되었음!");

        // 소켓을 받아 클라이언트의 요청을 처리하는, 별도의 메서드로 분리
        processRequest(socket); // try 안에 try문 실행 가능하다.

        System.out.println("-----클라이언트 요청 처리 끝-----");
      }

    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생!");
      return;
    }
  }

  static void processRequest(Socket clientSocket) {
    try(
        // 요청 처리가 끝난 후 클라이언트와 연결된 소켓을 자동으로 닫으려면
        // try문의 소괄호 안에 별도의 로컬 변수를 만들어, 받아 온 파라미터 객체를 담아야 한다.
        Socket socket = clientSocket;

        // 클라이언트가 보낸 메시지를 수신하고 클라이언트로 메시지를 전송할 입출력 도구 준비
        Scanner in = new Scanner(socket.getInputStream());
        // InputStream의 헬퍼 Scanner 사용

        PrintStream out = new PrintStream(socket.getOutputStream())
        // OutputStream의 데코레이터 PrintStream 사용
        ) {
      System.out.println("통신을 위한 입출력 스트림을 준비하였음!");

      String message = in.nextLine();
      System.out.println("클라이언트가 보낸 메시지: " + message);
      System.out.println("클라이언트가 보낸 메시지를 수신하였음!");
      // 클라이언트가 보낸 메시지를 수신한다.
      // 한 줄의 메시지를 읽을 때까지 리턴하지 않는다!

      // 클라이언트에게 메시지를 전송한다.
      // 클라이언트가 메시지를 모두 '읽을' 때까지 리턴하지 않는다!
      out.print("어서오세요~"); // 클라이언트가 보낸 메시지에 응답
      System.out.println("클라이언트로 메시지를 전송하였음!");

    } catch(Exception e) {
      System.out.println("예외 발생:");
      e.printStackTrace();
    }
  }
}