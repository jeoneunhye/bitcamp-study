package com.eomcs.oop.ex08.test;
// 클래스를 구성하는 클래스 멤버
public class B {
  // field(static/non-static)
  static int a; // 클래스가 메모리에 로딩될 때 자동 생성
  String b; // new 명령어를 실행하여 heap에 설계도가 생성됐을 때 접근 가능
  
  // method(static/non-static)
  static void m1() {}
  int m2() {return 0;}
  
  // initializer block(static/non-static)
  static {}
  {}
  
  // constructor
  B() {}
  
  // nested class(static/non-static)
  static class B1 {}
  class B2 {} // =inner class
}