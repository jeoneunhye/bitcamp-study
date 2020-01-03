package com.eomcs.oop.ex06.c;

public class B2 extends B {
  // 오버라이딩 문법
  // => 메서드명, 파라미터 형식, 리턴 타입이 같아야 한다.
  // => 파라미터의 이름은 달라도 된다. 상관없다.
  void m(float x) {
    // 그런데 이 메서드는 실수로 파라미터 타입을 int가 아닌 float으로 했다.
    // 이것은 오버라이딩이 아니라 오버로딩이 된 것이다.
    // 즉 float 파라미터를 받는 m() 메서드가 추가된 것이다.
    // 그런데 개발자는 오버라이딩(재정의)을 했다고 착각하고 사용할 것이다.
    System.out.println("B2의 m()");
  }
}