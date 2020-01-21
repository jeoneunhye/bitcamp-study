// 람다(lambda) 문법 - 아규먼트
package com.eomcs.oop.ex12;

public class Exam0310 {
  static interface Player {
    void play();
  }

  static void testPlayer(Player p) {
    p.play(); // Player 인터페이스 객체를 받아 play 메서드를 실행하는 메서드 testPlayer
  }

  public static void main(String[] args) {
    // 로컬 클래스
    class MyPlayer implements Player {
      @Override
      public void play() {
        System.out.println("실행!");
      }
    }

    testPlayer(new MyPlayer());
  }
}