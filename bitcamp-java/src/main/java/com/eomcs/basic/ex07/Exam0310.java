package com.eomcs.basic.ex07;

//# 메서드 : call by value
//
public class Exam0310 {

  static void swap(int a, int b) {
    System.out.printf("swap(): a=%d, b=%d\n", a, b);  // a=100, b=200
    int temp = a;
    a = b;
    b = temp;
    System.out.printf("swap(): a=%d, b=%d\n", a, b);  // a=200, b=100
  }

  public static void main(String[] args) {
    int a = 100;
    int b = 200;

    // swap() 호출할 때 a 변수의 값과 b 변수의 값을 넘긴다.
    // => 그래서 "call by value"라 부른다.
    // => 비록 swap()에서 a와 b라는 이름의 변수가 있지만,
    //    이 변수는 main()에 있는 변수와 다른 변수이다.
    swap(a, b);
    System.out.printf("main(): a=%d, b=%d\n", a, b);  // a=100, b=200 swap() 메서드를 실행한 메모리가 Stack에서 제거된다.
  }
}

// call by value (파라미터가 자바 원시 데이터 타입인 경우 모두!)
// => 자바에서는 primitive data type인 경우 메서드를 호출할 때 
//    '값'을 넘긴다.
// => 자바에서는 primitive data type에 대해서 
//    '메모리 주소'를 넘기는 방법이 없다.