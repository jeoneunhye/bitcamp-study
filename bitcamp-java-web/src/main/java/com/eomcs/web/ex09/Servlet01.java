// 보관소에 값 넣기
package com.eomcs.web.ex09;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ex09/s1")
@SuppressWarnings("serial")
public class Servlet01 extends HttpServlet {
  // HttpServlet을 구현하면 Servlet 인터페이스에서 정의한
  // Service(ServletRequest, ServletResponse)를 오버라이딩할 필요없다.
  // 이미 내부에서 구현되어 있다.
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 테스트 방법:
    // http://localhost:9999/bitcamp-java-web/ex09/s1

    // 1) ServletContext 보관소에 값 넣기
    // => ServletContext 객체는 웹 애플리케이션이 시작될 때 생성된다.
    ServletContext sc = this.getServletContext();
    sc.setAttribute("v1", "aaa"); // setAttribute(이름, 값) => 이름은 반드시 String, 값은 Object

    // 2) HttpSession 보관소에 값 넣기
    // => HttpSession 객체는 웹 브라우저에서
    //    '세션 아이디(예:고객번호, 스탬프 카드)'를 제공하지 않으면,
    //    getSession()을 호출할 때 생성된다.
    //    즉 이 요청을 한 클라이언트의 HttpSession 객체가 없다면 만들어 준다.
    // => 웹 브라우저에서 '세션 아이디'를 제공하면,
    //    getSession()을 호출할 때 기존에 생성했던 세션 객체를 리턴한다.
    //    즉 이미 이 클라이언트를 위해 만든 객체가 있다면 그 객체를 리턴한다.
    HttpSession session = request.getSession();
    session.setAttribute("v2", "bbb");

    // 3) ServletRequest 보관소에 값 넣기(파라미터로 받은 ServletRequest)
    // => ServletRequest 객체는 클라이언트가 요청할 때마다 생성된다.
    request.setAttribute("v3", "ccc");

    response.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("보관소에 값을 넣었습니다. - /ex09/s1");
  }
  // => Servlet02.java로 이동!
}