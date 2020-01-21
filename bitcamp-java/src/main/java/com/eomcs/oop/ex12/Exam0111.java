// 람다(lambda) 문법 - 익명 클래스
package com.eomcs.oop.ex12;

public class Exam0111 {
  static interface Player {
    void play();
  }

  public static void main(String[] args) {
    Player p1 = new Player() { // p1 변수에 바로 담아야 한다.
      @Override
      public void play() {
        System.out.println("실행!");
      }
    };

    p1.play(); // 메서드 실행이 가능해진다.
  }
}