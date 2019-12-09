package com.eomcs.basic.ex03;

// 리터럴(literal)
// - 자바 언어에서 값을 표기하는 문법
public class Exam1 {
  public static void main(String[] args) {
    // 정수 리터럴 => 무조건 10진수 값으로 출력된다
    System.out.println(78); // 10진수 표기법
    // 78이 정수 리터럴이다
    System.out.println(+78);  // 10진수 표기법 +는 생략되어 나옴
    System.out.println(-78);  // 10진수 표기법

    System.out.println(0116);  // 8진수 표기법. 0으로 시작 결과값 78
    System.out.println(-0116);  // 8진수 표기법 결과값 -78

    System.out.println(0x4e);  // 16진수 표기법. 0x로 시작 결과값 78
    System.out.println(-0x4e);  // 16진수 표기법

    System.out.println(0b01001110);  // 2진수 표기법. 0b로 시작 결과값 78
    
    // 부동 소수점 리터럴
    System.out.println(3.14); // 고정 소수점
    System.out.println(0.314e1);  // 부동 소수점 e1: 10의 1승
    System.out.println(31.4e-1);  // 부동 소수점 e-1: 10의 -1승  결과값은 모두 3.14로 같다

    // 논리 리터럴
    System.out.println(true);
    System.out.println(false);

    // 문자 리터럴
    System.out.println('A');
    System.out.println('가'); // 가나 안 됨! 문자 한 개만
    // 문자열 리터럴
    System.out.println("안녕하세요. 비트캠프!");
  }
}