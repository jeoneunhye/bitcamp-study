// 람다(lambda) 문법 - 리턴 문장
package com.eomcs.oop.ex12;

public class Exam0410 {
  static interface Interest { // 이자(소수점 다룸)
    double compute(int money);
  }

  static Interest getInterest(final double rate) { // 구현된 인터페이스 객체를 리턴하는 메서드
    // 로컬 클래스
    class MyInterest implements Interest {
      double rate;

      public MyInterest(double rate) {
        this.rate = rate;
      }

      @Override
      public double compute(int money) {
        return money + (money * rate / 100); // 원금 + 이자
      }
    }
    return new MyInterest(rate);
  }

  public static void main(String[] args) {
    Interest i1 = getInterest(1.5);
    System.out.printf("합계: %.1f\n", i1.compute(10_0000_0000)); // 10억 1500만원

    Interest i2 = getInterest(2.5);
    System.out.printf("합계: %.1f\n", i2.compute(10_0000_0000)); // 10억 2500만원
  }
}