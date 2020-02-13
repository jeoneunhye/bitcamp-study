// 서버와 입출력 테스트 - byte stream: data 주고 받기
package com.eomcs.net.ex03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client0140 {
  public static void main(String[] args) {
    Scanner keyScan = new Scanner(System.in);

    try (Socket socket = new Socket("localhost", 8888);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())) {

      System.out.println("서버와 연결되었음!");

      // 서버에 데이터를 보내기 전에 잠깐 멈춤!
      System.out.print(">");
      keyScan.nextLine();

      // 서버에 Data를 전송한다.
      out.writeInt(15_6789_1234);
      out.writeByte(100);
      out.writeFloat(3.14f);
      out.writeUTF("ABC가각간");
      // out.flush(); byte stream을 사용하는 경우 호출 생략해도 서버로 전송이 가능
      System.out.println("서버에 데이터를 보냈음!");

      // 서버에서 보낸 Data를 읽는다.
      int value = in.readInt();
      byte value2 = in.readByte();
      float value3 = in.readFloat();
      String value4 = in.readUTF();
      System.out.printf("%d, %d, %f, %s\n", value, value2, value3, value4);

    } catch (Exception e) {
      e.printStackTrace();
    }

    keyScan.close();
  }
}