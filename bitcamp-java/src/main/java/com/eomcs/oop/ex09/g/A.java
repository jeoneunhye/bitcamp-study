package com.eomcs.oop.ex09.g;

public interface A {
  void m1(); // public 추상 메서드 m1

  default void m2() {} // 나중에 추가되는 메서드 여기서 직접 구현. 구현중인 이전 클래스에 영향x

  static void m3() {
    System.out.println("오호라~~ 인터페이스도 스태틱 메서드를 가질 수 있네!");
  }
}