// 받은 쿠키(cookie) 읽기
package com.eomcs.web.ex10;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ex10/s2")
@SuppressWarnings("serial")
public class Servlet02 extends HttpServlet {
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 테스트 방법:
    // http://localhost:9999/bitcamp-java-web/ex10/s2

    // 클라이언트 보낸 쿠키 읽기
    // => 요청 헤더에 포함된 쿠키를 읽는다.
    // => HTTP 요청 프로토콜
    /*
          GET /bitcamp-java-web/ex10/s2 HTTP/1.1
          Host: localhost:9999
          Connection: keep-alive
          ...
          Cookie: name=hong; age=20; working=true; name2=홍길동; name3=%ED%99%8D%EA%B8%B8%EB%8F%99
     */

    // 쿠키 꺼내기
    // => 쿠키를 이름으로 한 개씩 추출할 수 없다.
    // => 한 번에 배열로 받아야 한다.
    // => 요청 헤더에 쿠키가 한 개라도 없으면 리턴되는 것은 빈 배열이 아닌 null이다.
    // => 따라서 무조건 반복문을 돌리면 안 된다.
    Cookie[] cookies = request.getCookies();

    response.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = response.getWriter();

    if (cookies != null) {
      for (Cookie c : cookies) {
        out.printf("%s=%s\n", c.getName(), c.getValue());
        if (c.getName().equals("name3")) {
          // 쿠키 값이 'URL 인코딩'한 값이라면 원래의 값으로 되돌리기 위해
          // 개발자가 직접 'URL 디코딩'해서 사용해야 한다.
          // 쿠키 값에 대해서 서버가 자동으로 디코딩해주지 않는다.
          // out.printf("  => %s\n", URLDecoder.decode(c.getValue(), "UTF-8"));
          // => 원본 데이터가 UTF-8이었다고 알려줌
          // => 혹시 글자가 깨진다면 사용할 것!
        }
      }
    }
  }
}