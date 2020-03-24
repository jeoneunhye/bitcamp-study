package com.eomcs.spring.ioc.ex13.c;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Caller {
  @Autowired X
  x;

  public void test() {
    System.out.println("test()..... 시작");

    int result = x.m1(10, 2);
    System.out.printf("result: %d\n", result);

    result = x.m1(10, 0);
    System.out.printf("result: %d\n", result);

    System.out.println("test()..... 끝");
  }

  /*
  test()..... 시작
  MyAdvice.doBefore()
  X.m1() 호출됨
  MyAdvice.doAfter()
  MyAdvice.doAfterReturning()
  result: 5
  MyAdvice.doBefore()
  X.m1() 호출됨
  MyAdvice.doAfter()
  MyAdvice.doAfterThrowing() => 10으로 0을 나눌 수 없다.
  오류 발생!
   */
}