package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class ServerAppTest {
  public static void main(String[] args) {
    String serverAddr = null;
    int port = 0;

    Scanner keyScan = new Scanner(System.in);

    try {
      System.out.print("서버? ");
      serverAddr = keyScan.nextLine();

      System.out.print("포트? ");
      port = Integer.parseInt(keyScan.nextLine());

    } catch (Exception e) {
      System.out.println("서버 주소 또는 포트 번호가 유효하지 않습니다.");
      keyScan.close();
      return;
    }

    try(
        Socket socket = new Socket(serverAddr, port);

        PrintStream out = new PrintStream(socket.getOutputStream());
        // Scanner in = new Scanner(socket.getInputStream())
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        // 데이터를 통째로 입력받도록 Decorator 교체
        ) {
      System.out.println("서버와 연결되었음!");

      System.out.print("서버에 전송할 메시지를 입력하세요: ");
      String sendMsg = keyScan.nextLine();

      out.println(sendMsg);
      System.out.println("서버에 메시지를 전송하였음!");

      // String message = in.nextLine();
      String message = in.readUTF();
      System.out.println("서버로부터 메시지를 수신하였음!");

      System.out.println("서버가 보낸 메시지: " + message);
      if (message.equals("OK")) {
        List<Board> list = (List<Board>) in.readObject(); // 데이터를 통째로 읽어옴

        for (Board b : list) {
          System.out.println(b);
          // Board 객체의 '주소'가 아닌 '값'을 출력하기 위해
          // Board 클래스에서 toString() 오버라이딩 필요
        }
      }

      System.out.println("서버와 연결을 정상적으로 끊음!");

    } catch (Exception e) {
      System.out.println("예외 발생:");
      e.printStackTrace();
    }

    keyScan.close();
  }
}