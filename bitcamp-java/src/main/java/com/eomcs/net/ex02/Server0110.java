// 서버 만들기
package com.eomcs.net.ex02;
// 서버(server)?
// => 네트워크 연결을 기다리는 쪽을 가리킨다.
import java.net.ServerSocket;
import java.util.Scanner;

public class Server0110 {
  public static void main(String[] args) throws Exception {
    Scanner keyboard = new Scanner(System.in);
    // 중간에 실행을 잠깐 멈출 때 사용하기 위함.

    System.out.println("서버 실행!");

    ServerSocket ss = new ServerSocket(8888);
    // 1) 네트워크 연결을 기다리는 역할을 수행할 객체 준비
    // => new ServerSocket(포트 번호)
    // 포트 번호
    // => 한 컴퓨터에서 네트워크 연결을 기다리는 프로그램의 식별 번호다.
    // => OS는 이 번호를 가지고 데이터를 받을 프로그램을 결정한다.

    System.out.println("클라이언트 연결을 기다리는 중...");

    // 잠깐 멈추기
    keyboard.nextLine(); // 사용자가 엔터를 칠 때까지 리턴하지 않는다.

    ss.close();
    System.out.println("서버 종료!");

    keyboard.close();
  }
}

// 포트 번호
// => 0 ~ 1023 (well-known port)
// - 특정 프로그램이 관습적으로 사용하는 포트 번호
// - 프로그램을 작성할 때 가능한 이 포트 번호를 사용하지 말아야 한다.
// - 7(echo), 20(FTP 데이터 포트), 21(FTP 제어 포트), 23(telnet),
//   25(SMTP)- e-mail, 53(DNS)- 이름에 대한 실제 IP 주소를 알려줘 웹브라우저에서 접속하는 용도,
//   80(HTTP), 110(POP3), 143(IMAP) 등
// => 1024 ~ 49151 (registered port)
// - 일반적인 통신 프로그램을 작성할 때 이 범위 포트 번호를 사용한다.
// - 다만 이 범위에 번호 중에서 특정 프로그램이 널리 사용하는 번호가 있다.
//   가능한 그 번호도 피하라!
// - 8080(proxy)- 같은 기업에서 다른 PC가 같은 데이터를 요구했을 때 내부에 보관해두고 리턴하여
//   속도를 높임, 서버를 경유하기 때문에 현재 내부 유출 등 보안 관련 모니터링 용도로 사용됨,
//   1521(Oracle), 3306(MySQL) 등
// => 49152 ~ 65535 (dynamic port)
// - 통신을 하는 프로그램은 반드시 포트 번호를 가져야 한다.
//   그래야 OS가 해당 프로그램을 구분할 수 있다.
// - 따라서 클라이언트 프로그램도 포트 번호를 갖는데
//   프로그램에서 결정하는 것이 아니라 OS로부터 자동 발급받는다.
// - 이 범위의 포트 번호는 클라이언트가 OS로부터 자동 발급받는 포트 번호다.