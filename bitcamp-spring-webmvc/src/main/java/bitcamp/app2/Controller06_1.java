// 예외 다루기
package bitcamp.app2;

import java.io.IOException;
import java.sql.SQLException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/c06_1")
public class Controller06_1 {
  // 테스트:
  // http://localhost:9999/bitcamp-spring-webmvc/app2/c06_1/get
  @GetMapping("get")
  public void get() {
    System.out.println("c06_1.get() 호출");
  }

  // 테스트:
  // http://localhost:9999/bitcamp-spring-webmvc/app2/c06_1/post
  @PostMapping("post")
  public void post() {
    System.out.println("c06_1.post() 호출");
  }

  // 테스트:
  // http://localhost:9999/bitcamp-spring-webmvc/app2/c06_1/error1
  @GetMapping("error1")
  public void error1() throws Exception {
    throw new Exception("request handler 오류 발생!");
    // Request Handler에서 예외를 던졌을 때 처리 절차
    // 1) 페이지 컨트롤러 안에 예외 처리기가 있다면,
    // => 해당 메서드를 호출한다.
    // 2) @ControllerAdvice 객체에 예외 처리기가 있다면
    // => 해당 메서드를 호출한다.
    // 3) web.xml에 지정된 오류 처리 기본 페이지가 설정되어 있다면
    // => 해당 페이지를 실행한다.
    // 4) 서블릿 컨테이너의 기본 오류 처리 페이지를 실행한다.
  }

  // 테스트:
  // http://localhost:9999/bitcamp-spring-webmvc/app2/c06_1/error2
  @GetMapping("error2")
  public void error2() throws Exception {
    throw new IOException("request handler 오류 발생!");
  }

  // 테스트:
  // http://localhost:9999/bitcamp-spring-webmvc/app2/c06_1/error3
  @GetMapping("error3")
  public void error3() throws Exception {
    throw new SQLException("request handler 오류 발생!");
  }

  // => GlobalControllerAdvice에서 설정한 에러 페이지가 뜬다.

  // 페이지 컨트롤러에 예외 처리기가 있으면 GlobalControllerAdvice에서 설정한 예외 처리기보다 우선 실행된다.
  // 주석 풀고 확인하기
  /*
  @ExceptionHandler
  public ModelAndView exceptionHandler(Exception ex) {
    ModelAndView mv = new ModelAndView();
    mv.addObject("error", ex);
    mv.setViewName("error6");
    return mv;
  }
   */
}