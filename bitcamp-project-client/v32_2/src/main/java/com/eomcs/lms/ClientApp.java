// LMS 클라이언트
package com.eomcs.lms;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
  public static void main(String[] args) {
    System.out.println("클라이언트 수업 관리 시스템입니다.");

    String serverAddr = null;
    int port = 0;

    // 키보드 스캐너 준비
    Scanner keyScan = new Scanner(System.in);

    try {
      // 사용자로부터 접속할 서버의 주소와 포트 번호를 입력받는다.
      System.out.print("서버? ");
      serverAddr = keyScan.nextLine();

      System.out.print("포트? ");
      port = Integer.parseInt(keyScan.nextLine()); // nextInt 다음에 올 nextLine과 겹치지 않도록

    } catch (Exception e) {
      System.out.println("서버 주소 또는 포트 번호가 유효하지 않습니다.");
      keyScan.close();
      return; // 더이상 메인 메서드를 실행하지 않는다.
    }

    try(
        // 서버와 연결
        Socket socket = new Socket(serverAddr, port); // 내 자신 "localhost" 또는 "127.0.0.1"

        // 소켓을 통해, 데이터를 읽고 쓰는 도구를 준비한다.
        // OutputStream out = socket.getOutputStream();
        // 바이트를 받아 write()하는 OutputStream 대신 PrintStream 도구를 사용한다.
        PrintStream out = new PrintStream(socket.getOutputStream());
        // OutputStream을 상속받은 Decorator 클래스 PrintStream
        // 파일 또는 OutputStream을 생성자 파라미터로 받아
        // 바이트를 write()하거나 데이터를 print()

        // InputStream in = sokect.getInputStream();
        // 바이트 배열이나 1바이트를 read()하는 InputStream 대신 Scanner 도구를 사용한다.
        Scanner in = new Scanner(socket.getInputStream())/*;*/
            // Scanner는 InputStream의 서브 클래스는 아니다. Decorator는 아니지만 helper 객체
            // 한 줄씩 입력받도록 Scanner 헬퍼 객체를 사용
        ) {
      System.out.println("서버와 연결되었음!");

      System.out.print("서버에 전송할 메시지를 입력하세요: ");
      String sendMsg = keyScan.nextLine();

      out.println(sendMsg);
      // 서버에 메시지를 전송한다.
      // => 서버가 메시지를 받을 때까지 리턴하지 않는다. 서버가 받아야 비로소 리턴한다.
      // => blocking 방식으로 동작한다.
      System.out.println("서버에 메시지를 전송하였음!");

      // 서버가 응답한 메시지를 수신한다.
      String message = in.nextLine(); // 서버가 보낸 메시지를 수신
      System.out.println("서버로부터 메시지를 수신하였음!");

      // 서버가 받은 메시지를 출력한다.
      System.out.println("서버가 보낸 메시지: " + message);

      System.out.println("서버와 연결을 정상적으로 끊음!");

    } catch (Exception e) {
      System.out.println("예외 발생:");
      e.printStackTrace();
    }

    keyScan.close(); // 키보드 객체 try문 밖에서 사용하기 때문에 따로 닫아줌

    /* Autocloseable 구현체 자원해제 문법 사용
    out.close();
    in.close();
    socket.close();
     */
  }
}