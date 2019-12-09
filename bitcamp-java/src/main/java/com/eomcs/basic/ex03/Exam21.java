package com.eomcs.basic.ex03;

// 정수 리터럴(literal)
// - 자바 언어에서 값을 표기하는 문법
public class Exam21 {
  public static void main(String[] args) {
    // 정수 리터럴 => 무조건 10진수 값으로 출력된다
    System.out.println(100); // 10진수 리터럴. 주로 사용
    System.out.println(0144); // 8진수 리터럴
    System.out.println(0b01100100); //2진수 리터럴
    System.out.println(0B1100100); //2진수 리터럴 맨앞 0이면 생략 가능
    System.out.println(0x64); //16진수 리터럴
    System.out.println(0X0064);  //16진수 리터럴 앞에 0붙여도 노상관
  }
}