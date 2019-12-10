package com.eomcs.basic.ex03;

// 논리 리터럴(literal)

public class Exam5 {
  public static void main(String[] args) {
    // true/false 소문자 
    System.out.println(true); //true
    System.out.println(false);  //false
    // System.out.println(TRUE);  컴파일 오류 발생 variable TRUE

    // 보통 비교 연산의 결과로 논리 값이 리턴된다.
    System.out.println(4 < 5);  //true
    System.out.println(4 > 5);  //false
    // 논리 연산의 결과도 논리 값이다.
    System.out.println(true && true); //true
    System.out.println(true && false);  //false
    System.out.println(true || true); //true
    System.out.println(true || false);  //true
    // 문자 코드와 '' 연산
    // '문자' 단독으로 사용될 때는 문자로 취급하지만
    // 다른 값과 연산을 수행하면 해당 문자 코드는 그냥 정수로 간주한다.
    System.out.println('가' == 44032);  //true
    System.out.println('가' == 44033);  //false
  }
}

// 논리 값을 다룰 때 메모리 크기
// JVM은 논리 값을 저장할 때 4바이트 정수 메모리 int를 사용한다.
// true = 1, false = 0을 저장
// 배열인 경우 1바이트 정수 메모리를 사용한다.