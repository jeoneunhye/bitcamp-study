// 컬렉션 타입의 프로퍼티 값 설정 - 배열
package com.eomcs.spring.ioc.ex05.a;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam01 {
  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "com/eomcs/spring/ioc/ex05/b/application-context.xml");

    System.out.println(iocContainer.getBean("c1"));
    System.out.println(iocContainer.getBean("c2"));

    // Car 객체 안에 Tire 배열 값을 넣어 설정하기
    // => array, list 태그 사용
  }
}