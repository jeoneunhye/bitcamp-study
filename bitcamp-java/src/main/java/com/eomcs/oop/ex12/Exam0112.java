// 람다(lambda) 문법 - functional interface를 효과적으로 다루는 문법
package com.eomcs.oop.ex12;

public class Exam0112 {
  // 다음과 같이 추상 메서드가 한 개 있는 인터페이스를 "functional interface"라고 부른다.
  // => 이런 경우에 람다 문법으로 사용할 수 있다.
  static interface Player {
    void play();
  }

  public static void main(String[] args) {
    Player p1 = () -> {
      System.out.println("Hello!");
    };

    p1.play(); // 메서드 실행이 가능해진다.
  }
}