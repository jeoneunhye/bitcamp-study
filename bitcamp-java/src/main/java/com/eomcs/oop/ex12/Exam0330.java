// 람다(lambda) 문법
package com.eomcs.oop.ex12;

public class Exam0330 {
  static interface Calculator {
    int compute(int a, int b);
  }

  static void test(Calculator c) {
    System.out.printf("결과: %d\n", c.compute(1, 10));
  }

  public static void main(String[] args) {
    // 여러 개의 문장일 경우 중괄호 생략 불가
    test((a, b) -> {
      int sum = 0;
      for (int i = a; i <= b; i++) {
        sum += i;
      }
      return sum;
    });
    // test()의 아규먼트로 Calculator 객체를 구현하여 넘겨줌
  }
}