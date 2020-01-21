// 람다(lambda) 문법 - 파라미터 I
package com.eomcs.oop.ex12;

public class Exam0130 {
  static interface Player {
    void play(String name);
  }

  public static void main(String[] args) {
    Player p1 = (String name) -> System.out.println("Hello, " + name + "!");
    p1.play("유관순a");

    // 파라미터의 타입을 생략할 수 있다.
    p1 = (name) -> System.out.println("Hello, " + name + "!");
    p1.play("유관순b");

    // 파라미터가 한 개일 때는 소괄호도 생략할 수 있다.
    // 파라미터가 없는 경우 괄호를 생략할 수 없다.
    p1 = name -> System.out.println("Hello, " + name + "!");
    p1.play("유관순c");
  }
}