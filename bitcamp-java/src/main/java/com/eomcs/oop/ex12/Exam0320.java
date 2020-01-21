// 람다(lambda) 문법
package com.eomcs.oop.ex12;

public class Exam0320 {
  static interface Calculator {
    int compute(int a, int b); // 두 개의 정수 값을 받고 정수 값을 리턴하는 메서드 compute
  }

  static void test(Calculator c) {
    System.out.printf("결과: %d\n", c.compute(200, 100));
  }

  public static void main(String[] args) {
    // compute(int, int)구현. return {}; 생략
    test((a, b) -> a + b); // 결과: 300
    test((a, b) -> a * b); // 결과: 20000
  }
}