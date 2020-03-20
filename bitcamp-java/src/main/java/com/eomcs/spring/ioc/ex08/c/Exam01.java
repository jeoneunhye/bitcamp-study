// 의존 객체 주입 자동화하기 - BeanPostProcessor 동작 원리
package com.eomcs.spring.ioc.ex08.c;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.eomcs.spring.ioc.SpringUtils;

public class Exam01 {
  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "com/eomcs/spring/ioc/ex08/c/application-context.xml");

    // Spring IoC 컨테이너에 들어 있는 객체 출력
    SpringUtils.printBeanList(iocContainer);

    // Car 객체를 꺼내 Engine 객체가 주입되었는지 확인해 보자!
    System.out.println(iocContainer.getBean("c1"));
  }
}