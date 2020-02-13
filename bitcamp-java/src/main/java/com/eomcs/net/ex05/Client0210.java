// connectionless 클라이언트 - 연결없이 데이터 송신
package com.eomcs.net.ex05;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client0210 {
  public static void main(String[] args) throws Exception {
    // connectionless 방식으로 통신을 수행할 소켓 생성
    DatagramSocket socket = new DatagramSocket();

    // 데이터를 받을 상대편 주소와 포트 번호
    String receiver = "localhost";
    int port = 8888;

    // 보낼 데이터가 담긴 바이트 배열을 준비
    // String message = new String("Hello"); // Heap에 String 객체 생성
    // String message = "Hello"; // constant pool에 String 객체 생성
    byte[] bytes = "Hello".getBytes("UTF-8"); // message 변수 생성하지 않고 String 객체 바로 사용

    // 보낼 데이터를 패킷에 담는다.
    // => 패킷 = 데이터 + 받는 이 주소 + 포트 번호
    DatagramPacket packet = new DatagramPacket(
        bytes, // 데이터가 저장된 바이트 배열
        bytes.length, // 전송할 데이터 갯수
        InetAddress.getByName(receiver), // 데이터를 받을 상대편 주소를 domain이면 ip주소로 바꿈
        port // 포트 번호
        );
    // InetAddress(콘크리트 클래스)의 생성자를 직접 사용할 수 없다면(private)
    // 팩토리 메서드(static)인 getByName에 String 주소를 담아 호출하면 InetAddress를 리턴한다.
    // 위와 다르게 주소와 포트 번호를 한 번에 담아 전송하고 싶으면
    // SocketAddress(추상 클래스)의 서브 클래스인 InetSocketAddress 구현 객체를 사용한다.

    // 데이터 전송
    socket.send(packet);
    System.out.println("데이터 전송 완료!");

    // 자원 해제
    socket.close();

    // 상대편이 네트워크에 연결되었는지 따지지 않고 무조건 데이터를 보낸다.
    // 서버가 켜있지 않은 상태에서 클라이언트를 작동해도 데이터 전송을 수행한다.
    // 만약 상대편이 연결되어 있지 않다면, 보낸 데이터는 그 쪽 네트워크에서 버려진다.
    // => 데이터 송수신을 보장하지 않는다.
  }
}