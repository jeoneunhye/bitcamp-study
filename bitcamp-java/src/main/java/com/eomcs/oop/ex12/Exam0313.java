// 람다(lambda) 문법 - 아규먼트
package com.eomcs.oop.ex12;

public class Exam0313 {
  static interface Player {
    void play();
  }

  static void testPlayer(Player p) {
    p.play();
  }

  public static void main(String[] args) {
    // 람다 적용
    testPlayer(() -> System.out.println("실행!"));
    /* 위와 같은 문장
        testPlayer(new Player() {
      @Override
      public void play() {
        System.out.println("실행!");
      }
    });
     */
  }
}