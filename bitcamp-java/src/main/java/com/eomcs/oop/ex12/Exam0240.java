// 람다(lambda) 문법
package com.eomcs.oop.ex12;

public class Exam0240 {
  static abstract class Player {
    public abstract void play();
  }

  public static void main(String[] args) {
    // 추상 클래스는 람다 문법 사용 불가
    Player p1 = () -> System.out.println("Hello!"); // 컴파일 오류!
    p1.play();
  }
}