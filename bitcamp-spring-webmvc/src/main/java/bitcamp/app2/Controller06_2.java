// 예외 다루기
package bitcamp.app2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/c06_2")
public class Controller06_2 {
  // 페이지 컨트롤러 안에 예외 처리기가 없으므로 @ControllerAdvice 객체에 있는 예외 처리기가 실행된다.
  // 테스트:
  // http://localhost:9999/bitcamp-spring-webmvc/app2/c06_2/error1
  @GetMapping("error1")
  public void error1() throws Exception {
    throw new Exception("request handler 오류 발생!");
  }
}