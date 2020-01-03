// 오버라이딩(overriding) - 준비
package com.eomcs.oop.ex06.c;

public class Exam0110 {
  public static void main(String[] args) {
    A obj1 = new A();
    obj1.name = "홍길동";

    obj1.print();   // 인스턴스 주소를 주고 메서드를 호출해야 한다.
  }
}