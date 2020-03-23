// 컬렉션 타입의 프로퍼티 값 설정 - Properties
package com.eomcs.spring.ioc.ex05.d;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam01 {
  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "com/eomcs/spring/ioc/ex05/d/application-context.xml");

    System.out.println(iocContainer.getBean("c1"));

    // Car 객체 안에 options Properties 객체의 값을 넣어 설정하기
    // => props, prop 태그 사용
  }
}