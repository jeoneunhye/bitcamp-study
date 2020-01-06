// 비트 연산자 &를 이용하여 % 연산 구현하기 응용 I
package com.eomcs.basic.ex05;

public class Exam03_52 {
  public static void main(String[] args) {
    // % 연산자를 이용하여 짝수/홀수 알아내기
    System.out.println(57 % 2 == 0 ? "짝수" : "홀수");
    
    // & 연산자를 이용하여 짝수/홀수 알아내기
    System.out.println((57 & 0x1) == 0 ? "짝수" : "홀수");
  }
}