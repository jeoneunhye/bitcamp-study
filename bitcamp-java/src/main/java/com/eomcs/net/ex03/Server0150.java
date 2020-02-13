// 클라이언트와 입출력 테스트 - byte stream
package com.eomcs.net.ex03;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server0150 {
  public static void main(String[] args) {
    try (Scanner keyboard = new Scanner(System.in);
        ServerSocket serverSocket = new ServerSocket(8888)) {

      System.out.println("클라이언트의 연결을 기다리고 있음.");

      try (Socket socket = serverSocket.accept();
          Scanner in = new Scanner(socket.getInputStream());
          PrintStream out = new PrintStream(socket.getOutputStream())) {

        System.out.println("클라이언트가 보낸 한 줄의 문자열을 기다리고 있음!");

        String str = in.nextLine(); // client: out.println("ABC가각간");
        System.out.println(str);

        // 서버가 데이터를 보내지 않으면 클라이언트의 read()는 리턴하지 않는다.
        // 이를 확인하기 위해 잠시 실행을 멈춘다.
        System.out.print(">");
        keyboard.nextLine();

        out.println(str);
        // out.flush(); byte stream을 사용하는 경우 호출 생략해도 서버로 전송이 가능
        System.out.println("클라이언트에게 데이터를 보냈음.");
      }

      System.out.println("클라이언트와의 연결을 끊었음.");

    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("서버 종료!");
  }
}