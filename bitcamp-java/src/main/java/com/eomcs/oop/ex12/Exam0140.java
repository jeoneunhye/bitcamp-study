// 람다(lambda) 문법 - 파라미터 II
package com.eomcs.oop.ex12;

public class Exam0140 {
  static interface Player {
    void play(String name, int age);
  }

  public static void main(String[] args) {
    Player p1 = (String name, int age) -> System.out.printf("Hello, %s(%d)!\n", name, age);
    p1.play("유관순a", 50);

    p1 = (name, age) -> System.out.printf("Hello, %s(%d)!\n", name, age);
    p1.play("유관순b", 50);

    // 파라미터가 두 개 이상이면 소괄호를 생략할 수 없다.
    /*
    p1 = name, age -> System.out.printf("Hello, %s(%d)!\n", name, age);
    p1.play("유관순", 50);
     */
  }
}