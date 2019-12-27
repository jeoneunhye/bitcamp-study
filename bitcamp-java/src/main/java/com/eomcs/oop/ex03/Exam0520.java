// 변수 초기화 - 초기화 문장
package com.eomcs.oop.ex03;

public class Exam0520 {
  static class A {
    // 변수 선언과 동시에 값을 저장하는 것을 "변수 초기화 문장"이라 부른다.
    // 변수 생성 즉시 "할당 연산자(=)"가 실행된다.
    // => 생성자보다 먼저 실행된다.
    static int a = 100;
    int b = 200;

    A() {
      b = 300;
    }
  }
  
  public static void main(String[] args) {
    // 초기화 문장들
    System.out.println(A.a);    // 100

    A obj = new A();
    System.out.println(obj.b);  // 300 생성자 b = 300; 주석 막으면 200?

    int c = 300;
    System.out.println(c);  // 300
  } 
}