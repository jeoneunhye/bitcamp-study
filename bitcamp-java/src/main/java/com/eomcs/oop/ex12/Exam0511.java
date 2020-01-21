// 메서드 레퍼런스(method reference) - 스태틱 메서드 레퍼런스
package com.eomcs.oop.ex12;

public class Exam0511 {
  static class MyCalculator {
    public static int plus(int a, int b) {return a + b;}
    public static int minus(int a, int b) {return a - b;}
    public static int multiple(int a, int b) {return a * b;}
    public static int divide(int a, int b) {return a / b;}
  }

  static interface Calculator1 {
    double compute(int a, int b);
  }

  static interface Calculator2 {
    float compute(int a, int b);
  }

  static interface Calculator3 {
    short compute(int a, int b);
  }

  static interface Calculator4 {
    void compute(int a, int b);
  }

  static interface Calculator5 {
    Object compute(int a, int b);
  }

  static interface Calculator6 {
    String compute(int a, int b);
  }

  static interface Calculator7 {
    Integer compute(int a, int b);
  }

  public static void main(String[] args) {
    // 메서드의 리턴 타입이 불일치
    Calculator1 c1 = MyCalculator::plus; // int -> double : OK! 암시적 형변환
    System.out.println(c1.compute(100, 200));
    Calculator2 c2 = MyCalculator::plus; // int -> float : OK! 암시적 형변환
    System.out.println(c2.compute(100, 200));
    // Calculator3 c3 = MyCalculator::plus; // int -> short : 컴파일 오류!
    Calculator4 c4 = MyCalculator::plus; // int -> void : OK!
    c4.compute(100, 200); // plus()의 리턴 값 무시
    Calculator5 c5 = MyCalculator::plus; // int -> Object : OK! auto-boxing
    System.out.println(c5.compute(100, 200));
    // Calculator6 c6 = MyCalculator::plus; // int -> String : 컴파일 오류!
    Calculator7 c7 = MyCalculator::plus; // int -> Integer : OK!

    // 메서드 레퍼런스를 지정할 때 리턴 타입 규칙:
    // 1) 같은 리턴 타입
    // 2) void : 리턴 값 무시
    // 3) 암시적 형변환 가능한 타입 (좁->넓)
    // 4) auto-boxing 가능한 타입
  }
}