// 쿠키(cookie) 읽기 - 사용 범위가 지정된 쿠키 읽기
package com.eomcs.web.ex10;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ex10_1/s24")
@SuppressWarnings("serial")
public class Servlet24 extends HttpServlet {
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 테스트 방법:
    // http://localhost:9999/bitcamp-java-web/ex10_1/s24

    Cookie[] cookies = request.getCookies();

    response.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = response.getWriter();

    if (cookies != null) {
      for (Cookie c : cookies) {
        out.printf("%s=%s\n", c.getName(), c.getValue());
      }
    }
    // => Servlet21에서 보관한 Cookie v3만 출력된다.
  }
}