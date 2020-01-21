// 람다(lambda) 문법 - 아규먼트
package com.eomcs.oop.ex12;

public class Exam0312 {
  static interface Player {
    void play();
  }

  static void testPlayer(Player p) {
    p.play();
  }

  public static void main(String[] args) {
    // 객체가 하나만 있기 때문에 메서드의 파라미터 안에 집어넣을 수 있다.
    // 넘겨주는 객체가 무슨 기능을 하는지 바로 확인이 가능 -> 유지 보수에 좋다.
    testPlayer(new Player() {
      @Override
      public void play() {
        System.out.println("실행!");
      }
    });
  }
}