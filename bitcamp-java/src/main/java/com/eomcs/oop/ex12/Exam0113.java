// 람다(lambda) 문법 - functional interface를 효과적으로 다루는 문법
package com.eomcs.oop.ex12;

public class Exam0113 {
  static interface Player {
    void play();
  }

  public static void main(String[] args) {
    Player p1 = /*()*/ -> System.out.println("Hello!");
    // 파라미터가 없다고 소괄호를 생략해서는 안 된다. 컴파일 오류!
    p1.play();
  }
}