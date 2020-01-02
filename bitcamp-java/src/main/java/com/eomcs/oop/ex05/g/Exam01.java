// 상속 - 수퍼 클래스에 기본 생성자가 없을 때!
package com.eomcs.oop.ex05.g;

public class Exam01 {
  public static void main(String[] args) {
    B obj = new B();
    System.out.printf("v1=%d, v2=%d\n", obj.v1, obj.v2);
    
    // 실행 결과
    // A(int) 생성자!
    // B() 생성자!
    // v1=100, v2=0
  }
}