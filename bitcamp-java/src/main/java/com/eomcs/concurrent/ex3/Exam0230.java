// Runnable 인터페이스 구현 + Thread - 람다(lambda)로 구현하기
package com.eomcs.concurrent.ex3;

public class Exam0230 {
  public static void main(String[] args) {
    // 인터페이스를 구현하고, 구현할 메서드가 하나일 때 람다 문법 적용 가능
    // 생성자 파라미터 앞에서 ctrl+space하면 구현한 인터페이스를 찾을 수 있다.
    new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        System.out.println("===> " + i);
      }
    }).start();

    for (int i = 0; i < 1000; i++) {
      System.out.println(">>>> " + i);
    }
  }
}