// 람다(lambda) 문법 - functional interface의 자격
package com.eomcs.oop.ex12;

public class Exam0220 {
  static interface Player { // play()만 구현하면 된다.
    void play();
    default void stop() {}
    static String info() {
      return "ok!";
    }
  }

  public static void main(String[] args) {
    Player p1 = () -> System.out.println("Hello!"); // 람다 구현 가능!
    p1.play();
  }
}