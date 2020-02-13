// stateless 방식 - 다중 클라이언트의 요청 처리시 문제점과 해결책
package com.eomcs.net.ex04.stateless3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class CalcServer {
  // 각 클라이언트의 작업 결과를 보관할 맵 객체
  // => Map<clientID, result>
  static Map<Long, Integer> resultMap = new HashMap<>();

  // 결국 클라이언트들의 요청을 순차적으로 처리하기 때문에
  // stateless 방식도 스레드를 사용하면 더 많은 클라이언트의 요청 처리가 가능하다.
  // 클라이언트와 대화하는 부분을 별도의 코드로 분리하여 실행한다.
  static class RequestHandler extends Thread {
    Socket socket;

    public RequestHandler(Socket socket) {
      this.socket = socket;
    }

    @Override
    public void run() {
      // JVM과 별개로 실행해야 하는 코드를 이 메서드에 둔다.
      try {
        processRequest(socket);

      } catch (Exception e) {
        System.out.println("클라이언트 요청 처리 중 오류 발생!");

      } finally {
        System.out.println("클라이언트 연결 종료!");
      }
    }
  }

  public static void main(String[] args) throws Exception {
    System.out.println("서버 실행 중...");

    ServerSocket ss = new ServerSocket(8888);

    while (true) {
      System.out.println("클라이언트의 연결을 기다림!");

      Socket socket = ss.accept();

      InetSocketAddress remoteAddr = (InetSocketAddress) socket.getRemoteSocketAddress();
      System.out.printf("클라이언트(%s:%d)가 연결되었음!\n",
          remoteAddr.getAddress(), remoteAddr.getPort());

      // 독립적으로 수행할 코드를 갖고 있는 스레드 객체를 생성한다.
      RequestHandler requestHandler = new RequestHandler(socket);

      // 그리고 작업을 실행시킨다.
      // => 스레드를 실행시킨 후 바로 리턴한다.
      requestHandler.start();

      System.out.printf("%s 클라이언트 요청을 스레드에게 맡겼습니다!\n",
          remoteAddr.getAddress());
    }

    // ss.close();
  }

  static void processRequest(Socket socket) throws Exception {
    try (Socket socket2 = socket;
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

      long clientId = in.readLong();

      String op = in.readUTF();
      int value = in.readInt();

      int result = 0;

      // 클라이언트를 위한 기존 값 꺼내기
      Integer obj = resultMap.get(clientId);

      if (obj != null) {
        System.out.printf("%d 고객 요청 처리!\n", clientId);
        result = obj;

      } else {
        clientId = System.currentTimeMillis();
        System.out.printf("%d 신규 고객 요청 처리!\n", clientId);
      }

      switch (op) {
        // 기존 계산 결과에 연산을 수행한다.
        case "+": result += value; break;
        case "-": result -= value; break;
        case "*": result *= value; Thread.currentThread().sleep(10000); break;
        case "/": result /= value; break;
      }

      // 클라이언트로 응답할 때 항상 클라이언트 아이디와 계산 결과를 출력한다.
      out.writeLong(clientId);
      out.writeInt(result);
      out.flush();

      // 아이디와 계산 결과를 resultMap에 보관한다.
      resultMap.put(clientId, result);
    }
  }
}