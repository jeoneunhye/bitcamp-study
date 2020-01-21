// 람다(lambda) 문법
package com.eomcs.oop.ex12;

public class Exam0230 {
  static interface Player {
    void play();
    void stop();
  }

  public static void main(String[] args) {
    Player p1 = () -> System.out.println("Hello!");
    // 컴파일 오류! 인터페이스의 추상 메서드가 한 개일 때만 람다 문법으로 구현 가능
    p1.play();
  }
}