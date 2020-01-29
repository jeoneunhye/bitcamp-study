// 람다(lambda) 문법 - 리턴 문장
package com.eomcs.oop.ex12;

public class Exam0412 {
  static interface Interest {
    double compute(int money);
  }

  static Interest getInterest(final double rate) { // 구현된 인터페이스 객체를 리턴하는 메서드
    // 익명 클래스
    Interest i = new Interest() {
      @Override
      public double compute(int money) {
        return money + (money * rate / 100);
      }
    };
    return i/*new MyInterest()*/;
  }

  public static void main(String[] args) {
    Interest i1 = getInterest(1.5);
    System.out.printf("합계: %.1f\n", i1.compute(10_0000_0000));

    Interest i2 = getInterest(2.5);
    System.out.printf("합계: %.1f\n", i2.compute(10_0000_0000));
  }
}