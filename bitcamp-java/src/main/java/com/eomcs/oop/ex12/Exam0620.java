// 메서드 레퍼런스(method reference) - 인스턴스 메서드 레퍼런스 구현 원리
package com.eomcs.oop.ex12;

public class Exam0620 {
  static class Calculator {
    double rate;

    public Calculator(double rate) {
      this.rate = rate;
    }

    public double year(int money) {
      return money * rate / 100;
    } // 일 년 치 원금 제외한 이자

    public double month(int money) {
      return money * rate / 100 / 12;
    } // 한 달 치 원금 제외한 이자

    public double day(int money) {
      return money * rate / 100 / 365;
    } // 하루 치 원금 제외한 이자
  }

  static interface Interest {
    double compute(int money); // 돈을 입력해서 이자를 계산받음
  }

  public static void main(String[] args) {
    // 인스턴스 메서드 레퍼런스로 Calculator 구현체를 만드는 방법
    Calculator 보통예금 = new Calculator(0.5); // 보통예금의 이율은 0.5%

    // Calculator c1 = 보통예금::year;
    // 위의 코드는 내부적으로 다음과 같다.
    Interest i1 = new Interest() {
      @Override
      public double compute(int money) {
        // 인스턴스 메서드 레퍼런스는 실제 인터페이스 구현체에서
        // 다음과 같이 메서드로 호출된다.
        return 보통예금.year(money); // 객체명.메서드명;
      }
    };

    System.out.println("[보통예금]");
    i1 = 보통예금::year;
    System.out.printf("연 이자: %.1f\n", i1.compute(10_0000_0000));

    i1 = 보통예금::month;
    System.out.printf("월 이자: %.1f\n", i1.compute(10_0000_0000));

    i1 = 보통예금::day;
    System.out.printf("일 이자: %.1f\n", i1.compute(10_0000_0000));
  }
}