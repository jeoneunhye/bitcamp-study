// IoC 컨테이너에 객체를 보관하기 I
package com.eomcs.spring.ioc.ex01.e;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.eomcs.spring.ioc.SpringUtils;

public class Exam01 {
  public static void main(String[] args) {
    ApplicationContext iocContainer =
        new ClassPathXmlApplicationContext("com/eomcs/spring/ioc/ex01/e/application-context.xml");

    // 현재 IoC 컨테이너에 들어있는 객체를 출력해 보자.
    SpringUtils.printBeanList(iocContainer);
    // application-context.xml에 기술한 Car 객체 1개가 출력된다.

    System.out.println("실행 완료!");
  }
}