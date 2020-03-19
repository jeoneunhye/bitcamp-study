// 생성자의 파라미터 값을 형변환할 수 없는 경우 - 예외 발생
package com.eomcs.spring.ioc.ex03.c;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam01 {
  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "com/eomcs/spring/ioc/ex03/c/application-context.xml");

    // Car 클래스와 비교하며 어떤 생성자가 호출되는지 확인하기

    // SpringUtils.printBeanNames(iocContainer);
  }
}