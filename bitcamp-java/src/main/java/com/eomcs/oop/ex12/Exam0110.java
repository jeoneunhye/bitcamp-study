// 람다(lambda) 문법 - 로컬 클래스
package com.eomcs.oop.ex12;

public class Exam0110 {
  static interface Player {
    void play();
  }

  public static void main(String[] args) {
    Player p1; // 인터페이스 레퍼런스는 인터페이스를 구현한 클래스의 객체를 담아야 한다.

    class MyPlayer implements Player {
      @Override
      public void play() {
        System.out.println("실행!");
      }
    }

    p1 = new MyPlayer();
    p1.play(); // 메서드 실행이 가능해진다.
  }
}