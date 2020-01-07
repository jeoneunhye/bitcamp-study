package com.eomcs.oop.ex08.test;
// B는 package member class
// public만 아니라면 한 소스파일 안에 클래스를 여러 개 만들 수 있다. -> 부적절
class A1 {
  
}
// C는 package member class
class A2 {
  
}
// A는 package member class
public class A { // public 클래스는 소스 파일과 클래스명이 반드시 일치해야 한다.
  
  //클래스 안에 선언된 클래스: nested 중첩 클래스-static/non-static(inner) 실무에서는 구분 없이 inner라고 부른다.
  class X {}
  
  static class X2 {}

  void m() {
    class Y {} // 특정 메서드 안에 있는 클래스: local 클래스
    
    Object obj = new Object() {
      // Object를 상속받은 익명 서브 클래스. 정의하는 즉시 인스턴스를 만들어야 한다
      @Override
      public String toString() {
        return "익명클래스";
      }
    };
  }
}