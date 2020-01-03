package com.eomcs.oop.ex06.c;

public class X4 extends X3 {
  @Override
  void m1() {
    System.out.println("X4의 m1()");
  }

  void test() {
    this.m1(); // 이 클래스에 있다. X4의 m1()
    super.m1(); // X3 수퍼 클래스에 없다. X2 수퍼 클래스에 있다. X2의 m1()

    this.m2(); // 이 클래스에 없다. X3의 m2()
    super.m2(); // X3 수퍼 클래스에 있다. X3의 m2()
    
    //super.super.m1(); // 컴파일 오류! 이런 문법은 없다! 무협지 문법!
  }
}