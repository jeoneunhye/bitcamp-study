// non-static nested class = inner class
package com.eomcs.oop.ex11.a;

public class Exam0220 {
  // 인스턴스 멤버
  class A {
    // 컴파일러가 추가하는 필드 및 생성자
    /*
     ex) Exam0220 this;
    
     public A(Exam0220 obj) {
     this = obj;
     }
     */
    void m1() {
    }
  }
  
  int iValue;
  void im() {}
  void m2() {
    // 인스턴스 멤버는 스태틱 멤버를 사용할 수 있다.
    sValue = 100;
    sm();

    // 인스턴스 멤버는 this라는 내장 변수가 있기 때문에 다른 인스턴스 멤버를 사용할 수 있다.
    this.iValue = 100;
    this.im();
    iValue = 100; // this 생략 가능
    im(); // this 생략 가능

    // this.A obj; // 컴파일 오류! inner class의 변수를 선언할 때는 this를 붙이지 않는다.
    A obj;
    obj = this.new A(); // 바깥 클래스의 인스턴스 주소 this를 가지고 inner class A의 인스턴스를 생성
    // 컴파일러는 inner class를 생성하는 위 코드를 다음과 같이 바꾼다.
    // A 클래스의 기본 생성자가 호출되는 것이 아니다.
    // 컴파일러가 바깥 클래스의 객체 주소를 받으려고 추가한
    // 생성자를 호출하는 코드로 바뀐다.
    // new A(this);
    obj = new A(); // 다른 인스턴스 멤버를 사용하는 것처럼 this를 생략할 수 있다.
    // 컴파일러는 위 코드를 다음과 같이 바뀐다.
    // new A(this);
  }
  
  // 스태틱 멤버
  static int sValue;
  static void sm() {}
  static void m1() {
    // 스태틱 멤버는 다른 스태틱 멤버를 사용할 수 있다.
    sValue = 100;
    sm();

    // 스태틱 멤버는 this라는 내장 변수가 없기 때문에 인스턴스 멤버를 사용할 수 없다.
    // iValue = 100; // 컴파일 오류! 붙일 this가 없다.
    
    final A obj; // 단, inner class의 레퍼런스를 만드는 것은 허용한다.
    // obj = new A(); // 컴파일 오류! 인스턴스 없이 inner class의 생성자를 호출할 수 없다.
  }

  public static void main(final String[] args) {
    final Exam0220 obj = new Exam0220();

    obj.m2();
    System.out.println(obj.iValue);
    System.out.println(sValue); // Exam0220.sValue
  }
}