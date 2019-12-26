package com.eomcs.oop.ex01;

// 컴파일하면 블록 단위로 클래스파일이 생성된다.
//# 클래스 사용 - 사용 범위

class A {}
// 패키지 멤버 클래스
// => 패키지에 직접 소속된 클래스이다.
// => 같은 패키지라면 다른 클래스들도 A 클래스를 사용할 수 있다.
// bin/main/com/eomcs/oop/ex01/A.class

public class Exam0420 { // Exam0420.class

  class B {}    // Exam0420$B.class
  // 중첩 클래스(nested class)
  // => 다른 클래스 안에 정의된 클래스
  // => 그 클래스 안에서만 사용된다.

  public static void main(String[] args) {

    class C {}  // Exam0420$1C.class : 첫번째 로컬 클래스 C라는 뜻. 파일명에 어느 메서드인지는 따로 나오지 않는다
    // 로컬 클래스(local class)이면서 네스티드 클래스
    // => 메서드 블록 안에 정의된 클래스
    // => 오직 그 메서드 블록 안에서만 사용된다.
  }
  
  public void m1() {
    A ref1;
    B ref2;
    //C ref3; // 컴파일 오류!
  }
}
