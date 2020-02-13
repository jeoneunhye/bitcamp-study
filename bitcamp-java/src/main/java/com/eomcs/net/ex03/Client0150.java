// 서버와 입출력 테스트 - byte stream
package com.eomcs.net.ex03;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client0150 {
  public static void main(String[] args) {
    Scanner keyScan = new Scanner(System.in);

    try (Socket socket = new Socket("localhost", 8888);
        PrintStream out = new PrintStream(socket.getOutputStream());
        // byte stream을 받는 PrintStream
        Scanner in = new Scanner(socket.getInputStream())) {

      System.out.println("서버와 연결되었음!");

      // 서버에 데이터를 보내기 전에 잠깐 멈춤!
      System.out.print(">");
      keyScan.nextLine();

      out.println("ABC가각간");
      // out.flush(); byte stream을 사용하는 경우 호출 생략해도 서버로 전송이 가능
      System.out.println("서버에 데이터를 보냈음!");

      String str = in.nextLine(); // server: out.println(str);
      System.out.println(str);

    } catch (Exception e) {
      e.printStackTrace();
    }

    keyScan.close();
  }
}