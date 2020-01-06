// 비트 연산자 &를 이용하여 % 연산 구현하기
package com.eomcs.basic.ex05;

public class Exam03_51 {
  public static void main(String[] args) {
    System.out.println(57 % 2); // 1
    System.out.println(57 & 0b1);   // 1
    // 어떤 값에 대해 2로 나눈 나머지 값을 구하고 싶다면
    // & 연산자를 이용하여 그 값의 하위 1비트 값만 추출하면 된다.
    
    System.out.println(57 % 4); // 1
    System.out.println(57 & 0b11);  // 1
    // 어떤 값에 대해 4로 나눈 나머지 값을 구하고 싶다면
    // & 연산자를 이용하여 그 값의 하위 2비트 값만 추출하면 된다.
    
    System.out.println(57 % 8); // 1
    System.out.println(57 & 0b111);  // 1
    
    System.out.println(57 % 16);    // 9
    System.out.println(57 & 0b1111);    // 9
  }
}