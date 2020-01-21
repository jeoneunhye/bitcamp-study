// 메서드 레퍼런스(method reference) - 스태틱 메서드 레퍼런스
package com.eomcs.oop.ex12;

public class Exam0510 {
  static class MyCalculator {
    public static int plus(int a, int b) {return a + b;}
    public static int minus(int a, int b) {return a - b;}
    public static int multiple(int a, int b) {return a * b;}
    public static int divide(int a, int b) {return a / b;}
  }

  static interface Calculator {
    int compute(int a, int b);
  }

  public static void main(String[] args) {
    // 메서드 한 개짜리 인터페이스의 구현체를 만들 때
    // 기존 스태틱 메서드를 람다 구현체로 사용할 수 있다.
    // 단 인터페이스에 선언된 메서드의 규격과 일치해야 한다.
    // 규격? 파라미터 타입 및 갯수, 리턴 타입
    // 문법:
    // 클래스명::메서드명;

    // 인터페이스를 구현한 익명 클래스 객체가 내부에 저장되어있다.
    // (compute를 호출하여, 새로 만들지 않고 재활용)
    Calculator c1 = MyCalculator::plus;
    Calculator c2 = MyCalculator::minus;
    Calculator c3 = MyCalculator::multiple;
    Calculator c4 = MyCalculator::divide;

    System.out.println(c1.compute(200, 17)); // compute() ==> plus()
    System.out.println(c2.compute(200, 17)); // compute() ==> minus()
    System.out.println(c3.compute(200, 17)); // compute() ==> multiple()
    System.out.println(c4.compute(200, 17)); // compute() ==> divide()
  }
}