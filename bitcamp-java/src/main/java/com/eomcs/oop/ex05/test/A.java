package com.eomcs.oop.ex05.test;
// +Test01.java
public class A {
  int v1;
  
  A() {
    this(100);  // ★아래의 생성자를 호출. 같은 생성자끼리만 가능하고 반드시 첫번째 문장에 와야 한다.
    this.v1 = 100;  // 위의 this와는 다른 기능- 인스턴스 주소를 불러온다
    System.out.println("A()");
  }
  
  A(int v1) {
    this.v1 = v1;
    System.out.println("A(int)");
  }
}