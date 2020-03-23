// 팩토리 메서드 호출 - FactoryBean 구현체
package com.eomcs.spring.ioc.ex06.e;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam01 {
  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "com/eomcs/spring/ioc/ex06/e/application-context.xml");

    // 이 예제는 d 패키지의 Exam01 예제와 같다.
    // 다만 Factory 클래스의 이름을 CarFactory에서 CarFactoryBean으로 바꾼 것이다.
    System.out.println(iocContainer.getBean("c1"));

    // 타입으로 찾을 때
    // FactoryBean의 getObjectType()이 호출된다.
  }
}