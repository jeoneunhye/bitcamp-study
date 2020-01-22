// 던지는 예외 받기 - 예외 처리 책임을 상위 호출자에게 위임
package com.eomcs.exception.ex3;

import java.io.IOException;
import java.sql.SQLException;

public class Exam0420 {
  static void m(int i)
      throws Exception, RuntimeException, SQLException, IOException {
    if (i == 0)
      throw new Exception();
    else if (i == 1)
      throw new RuntimeException();
    else if (i == 2)
      throw new SQLException();
    else
      throw new IOException();
  }

  public static void main(String[] args) throws Exception {
    // 예외 처리 방법 1:
    // 예외를 처리하고 싶지 않다면 상위 호출자에게 책임을 떠넘길 수 있다.
    // 컴파일 오류는 발생하지 않지만,
    // => main() 호출자는 JVM이고
    //    JVM은 main()에서 던진 예외를 받는 순간 즉시 실행을 멈춘다.
    //    그래서 main()의 호출자에게 책임을 떠넘기는 것은 바람직하지 않다.
    // => 즉 main()은 예외 처리의 마지막 보루다.
    //    main()에서 마저 예외 처리를 하지 않으면 프로그램은 멈.춘.다!
    m(1); // m(1); 메서드에 try ~ catch 문법이 적용되어 있지 않다.
  }
}