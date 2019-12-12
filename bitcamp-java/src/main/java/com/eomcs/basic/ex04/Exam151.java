package com.eomcs.basic.ex04;

public class Exam151 {
public static void main(String[] args) {
  // 변수 사용
  int i;
  i = 100;

  System.out.println(i);

  int j;
  j = i;  // i 변수에 들어있는 값을 j 변수에 넣어라!

  // 할당 연산자 '='
  // 변수에 값을 넣는 일을 한다.
  // 문법 l-value = r-value;
  //  l-value : 변수가 와야 한다. 값이 올 수 없다.
  //  r-value : 값이나 변수, 표현식이 올 수 있다.

  j = 300;  // (O) 값
  j = i;  // (O) 변수
  j = i * 3;  // (O) 표현식
  j = Math.abs(-200); // (O) 표현식 절대값 출력
  j = (i > 100) ? 1 : -1; // (O) 표현식 i가 100보다 크면 1을 집어넣고 아니면 -1을 넣어라
  //j = System.out.println(100);  // (X) 컴파일 오류 incompatible types: void cannot be converted
                                // 그냥 문장이다. 표현식이 아니다
  //  문장(statement)
  //  자바 언어로 작성한 명령어

  // 표현식(expression)
  // 문장 중에 값을 리턴하는 문장
  }
}