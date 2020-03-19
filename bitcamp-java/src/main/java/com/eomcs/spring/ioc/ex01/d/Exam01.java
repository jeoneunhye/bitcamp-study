// IoC 컨테이너에 보관된 객체를 확인하기
package com.eomcs.spring.ioc.ex01.d;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.eomcs.spring.ioc.SpringUtils;

public class Exam01 {
  public static void main(String[] args) {
    ApplicationContext iocContainer =
        new ClassPathXmlApplicationContext("com/eomcs/spring/ioc/ex01/d/application-context.xml");

    // 현재 IoC 컨테이너에 들어있는 객체를 출력해 보자.
    SpringUtils.printBeanList(iocContainer);
    // application-context.xml에 아무런 객체를 기술하지 않았기 때문에 0개가 출력된다.

    System.out.println("실행 완료!");
  }
}