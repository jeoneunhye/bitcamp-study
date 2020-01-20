// non-static nested class = inner class
package com.eomcs.oop.ex11.a;

public class Exam0210 {
  // 스태틱 멤버
  static int sValue;
  static void sm() {}

  // 인스턴스 멤버
  int iValue;
  void im() {}

  // non-static nested class는 인스턴스 멤버다.
  // 따라서 인스턴스가 있어야만 사용할 수 있다.
  class A {
    // 자바 컴파일러는 non-static nested class를 컴파일할 때
    // 바깥 클래스의 인스턴스 주소를 담을 필드 this를 자동으로 생성한다.
    // ex) Exam0210 this;
    // 또한 이 inner class의 객체를 생성할 때 호출될 생성자를 자동으로 만든다.
    /*
     * ex) Exam0210$1A.class public A(Exam0210 arg0) { this = arg0; (this는 Exam0210 인스턴스를 담는 필드)
     * this에 arg0 주소가 보관된다. }
     */

    void m1() {
      // 인스턴스 멤버(인스턴스 블록, 생성자, 인스턴스 메서드, inner class)는
      // 스태틱 멤버를 자유롭게 사용할 수 있다.
      sValue = 100; // OK
      sm(); // OK

      // 인스턴스 멤버는 같은 인스턴스 멤버를 사용할 수 있다.
      // 왜?
      // inner class의 객체를 생성할 때
      // 컴파일러가 자동으로 추가한 변수(ex: this)에
      // 바깥 클래스의 객체 주소가 저장된다.
      // 이렇게 inner class 객체에 보관된 바깥 클래스의 객체를 사용하려면
      // 다음과 같은 이름으로 변수를 사용해야 한다.
      // "바깥클래스명.this"
      // 다음 코드를 보자!
      Exam0210.this.iValue = 100;
      im();

      // inner 클래스에 같은 이름의 멤버가 없다면
      // "바깥 클래스명.this"를 생략해도 된다.
      iValue = 100;
      im();

      // 주의!
      // "바깥클래스명"을 빼고 그냥 this 변수를 사용하면
      // 이 때의 this는 inner class 즉 A의 인스턴스를 가리킨다.
      // 바깥 클래스의 인스턴스를 가리키고 싶으면 반드시 바깥 클래스명을 지정해야 한다.
      // this.iValue = 100; // 컴파일 오류!
      // this.im(); // 컴파일 오류!
    }
  }

  public static void main(final String[] args) {
  }
}

// 결론
// nested class가 인스턴스 멤버를 사용한다면
// non-static nested class로 정의하라!