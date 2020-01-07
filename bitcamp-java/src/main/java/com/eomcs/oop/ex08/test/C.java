package com.eomcs.oop.ex08.test;

public class C {
  // 스태틱 멤버는 인스턴스 없이 호출한다.
  // 즉 스태틱 멤버(static context문맥)에서는 this를 사용할 수 없다.
  static void m1() {
    // Object obj = this; // this라는 변수가 존재하지 않는다.
  }
  
  static {
    //Object obj = this; // 컴파일 오류
  }
  
  // 인스턴스 멤버는 인스턴스 주소가 있어야만 사용할 수 있다.
  // 인스턴스 멤버를 사용할 때 넘겨준 인스턴스 주소는 this 내장 변수에 저장되어 있다.
  int m2() {
    Object obj = this; // OK
    return 0;
    }
  
  {
    Object obj = this; // OK
  }
  
  C() {
    Object obj = this; // OK
  }
}