package com.eomcs.oop.ex06.c;

public class X3 extends X2 {
  @Override
  void m2() {   // X2의 수퍼 클래스인 X 클래스에서 메서드를 가져와 오버라이딩한 것
    System.out.println("X3의 m2()");
  }
}