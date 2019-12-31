package com.eomcs.oop.ex03.test;
// 컴파일하면 클래스 파일이 어떻게 변하는 지 확인하기
public class Test03 {
  static int s1;
  int s2;
  
  static int i1;
  int i2;
  
  static void m1(int c) {
    int a, b;
    // 변수 선언 시 클래스파일에 변화가 없다-최적화 쓰지 않는 변수 optimizing

    a = 100;    // Local variable table에 a가 비로소 추가된다.
    b = 200;    // Local variable table에 b가 추가된다.
    // bipush 100 임시그릇에 100을 넣고
    // istore_0 0번째인 a에 저장해라
    
    // 파라미터에 int c를 넣으면
    // a와 b보다 먼저 저장된다. c가 0번이 됨
  }
  
  static void x(int a) {
    // int a가 변수 테이블 0번에 들어간다.
  }
  
  void m2(int a) {
    // 클래스 메서드 m1과 달리 Local variable table에 this라는 내장 변수가 0번에 발견된다.
    // this의 data type은 Test02
    // 파라미터에 int a를 추가하면 변수 테이블 1번에 들어간다.
  }
}