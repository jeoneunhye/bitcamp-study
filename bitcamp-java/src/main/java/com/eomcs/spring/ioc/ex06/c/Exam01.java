// 팩토리 메서드 호출 - non-static 메서드 호출
package com.eomcs.spring.ioc.ex06.c;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam01 {
  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "com/eomcs/spring/ioc/ex06/c/application-context.xml");

    System.out.println(iocContainer.getBean("c1"));

    // non-static 팩토리 메서드를 호출하고 생성한 객체를 리턴받기
    // => factory-bean, factory-method 속성 사용
  }
}