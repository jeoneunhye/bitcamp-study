// 오버라이딩(overriding) - this와 super로 메서드를 호출하는 원리
package com.eomcs.oop.ex06.c;

public class Exam0420 {
  public static void main(String[] args) {
    X4 obj = new X4();
    obj.test();
    // 결과값
    // X4의 m1()
    // X2의 m1()
    // X3의 m2()
    // X3의 m2()
  }
}

// this.메서드() 호출?
// => 현재 클래스부터 호출할 메서드를 찾는다.
// super.메서드() 호출?
// => 부모 클래스부터 호출할 메서드를 찾는다.