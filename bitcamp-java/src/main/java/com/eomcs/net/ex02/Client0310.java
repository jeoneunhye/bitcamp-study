// 타임아웃 시간 설정하기
package com.eomcs.net.ex02;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class Client0310 {
  public static void main(String[] args) throws Exception {
    Scanner keyScan = new Scanner(System.in);
    // 실행을 잠시 중단시키기 위해 사용

    System.out.println("클라이언트 실행!");

    // 1) 소켓을 생성한다.
    Socket socket = new Socket();
    System.out.println("소켓 생성됨.");

    // 2) 연결할 서버의 주소를 준비한다.
    SocketAddress socketAddress = new InetSocketAddress("localhost", 8888);
    // SocketAdress는 추상 클래스기 때문에 서브 클래스인 InetSocketAdress 객체를 생성

    // 3) 서버와의 연결을 시도한다.
    // => 타임아웃으로 지정된 시간 안에 서버와 연결되지 않으면 즉시 예외가 발생한다.
    System.out.println("서버와 연결 중...");
    socket.connect(socketAddress, 5000); // timeout : milliseconds
    // connect(SocketAddress): String 주소를 받을 수 없다.
    System.out.println("서버와 연결되었음!");

    keyScan.nextLine(); // 사용자가 엔터를 칠 때까지 다음 코드로 이동하지 않는다.

    socket.close();
    System.out.println("서버와의 연결을 끊었음.");

    keyScan.close();
  }
}