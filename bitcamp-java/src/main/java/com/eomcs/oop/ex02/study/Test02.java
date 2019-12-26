package com.eomcs.oop.ex02.study;

public class Test02 {
  public static void main(String[] args) {
    // 식1: 2 + 3 - 1 * 7 / 3 = ?
    // 식2: 3 * 2 + 7 / 4 - 5 = ?
    //    int result = 0;
    // 연산자 우선 순위 무시하고 계산해보자
    // result = Calculator.plus(2, 3); result 넘기기 전 식

    //        Calculator.plus(2);
    //        Calculator.plus(3);
    //        
    //        Calculator.plus(3);
    //        Calculator.multiple(2);
    //        
    //        Calculator.minus(1);
    //        Calculator.plus(7);
    //        
    //        Calculator.multiple(7);
    //        Calculator.divide(4);
    //        
    //        Calculator.divide(3);
    //        Calculator.minus(5);
    // Calculator의 result 변수는 static이라서 클래스에 한 개만 존재한다.
    // 따라서 다음과 같이 여러 개의 결과를 동시에 처리할 수 없다.
    // 오직 한 번에 한 개의 결과만 관리할 수 있다.
    // 그래서 한 개의 식을 끝낸 다음에 다른 식을 계산해야 한다.
    //    System.out.printf("결과: %d\n", Calculator.result);
    //    System.out.printf("결과: %d\n", Calculator.result);
    
    // 결과를 개별적으로 관리하고 싶다면 non-static field로 선언하라!
    // 즉 인스턴스마다 따로 생성되게 하라!
    // 인스턴스 필드로 만들면 된다.
    Calculator c1 = new Calculator();
    Calculator c2 = new Calculator();

    // 메서드를 호출할 때 어떤 result 변수에 그 계산 결과를 저장할 것인지
    // 파라미터로 인스턴스 주소를 넘겨야 한다.
//    Calculator.plus(c1, 2);
//    Calculator.plus(c2, 3);
//
//    Calculator.plus(c1, 3);
//    Calculator.multiple(c2, 2);
//
//    Calculator.minus(c1, 1);
//    Calculator.plus(c2, 7);
//
//    Calculator.multiple(c1, 7);
//    Calculator.divide(c2, 4);
//
//    Calculator.divide(c1, 3);
//    Calculator.minus(c2, 5);
//    System.out.printf("식1의 결과: %d\n", c1.result);
//    System.out.printf("식2의 결과: %d\n", c2.result);
    
    //-> 결과가 잘 나오지만 더 직관적으로 만들기 위해! 인스턴스 메서드로 변경
    
    c1.plus(2);
    c2.plus(3);

    c1.plus(3);
    c2.multiple(2);

    c1.minus(1);
    c2.plus(7);

    c1.multiple(7);
    c2.divide(4);

    c1.divide(3);
    c2.minus(5);
    System.out.printf("식1의 결과: %d\n", c1.result);
    System.out.printf("식2의 결과: %d\n", c2.result);
  }
}