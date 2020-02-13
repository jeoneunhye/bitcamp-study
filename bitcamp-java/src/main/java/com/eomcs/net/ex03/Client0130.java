// 서버와 입출력 테스트 - byte stream: data 주고 받기
package com.eomcs.net.ex03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client0130 {
  public static void main(String[] args) {
    Scanner keyScan = new Scanner(System.in);

    try (Socket socket = new Socket("localhost", 8888);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())) {

      System.out.println("서버와 연결되었음!");

      // 서버에 데이터를 보내기 전에 잠깐 멈춤!
      System.out.print(">");
      keyScan.nextLine();

      // 서버에 보낼 int 값을 준비한다.
      out.writeInt(15_6789_1234);
      // out.flush(); byte stream을 사용하는 경우 호출 생략해도 서버로 전송이 가능
      System.out.println("서버에 데이터를 보냈음!");

      // 서버에서 보낸 int 값을 읽는다.
      int value = in.readInt();
      System.out.println(value);

    } catch (Exception e) {
      e.printStackTrace();
    }

    keyScan.close();
  }
}