// non-static nested class = inner class
package com.eomcs.oop.ex11.a;

class Exam0230_X {
  int iValue;
  void im() {}

  class A {
    // 컴파일러가 바깥 클래스를 받을 필드 및 생성자 자동 생성
    /* ex)
    Exam0230_X this;

    public A(Exam0230_X obj) {
      this = obj;
      }
     */
    void m1() {

    }
  }
}

public class Exam0230 {
  public static void main(final String[] args) {
    // 다른 클래스의 인스턴스 멤버는 반드시 인스턴스가 있어야만 사용할 수 있다.
    //Exam0230_X.iValue = 100; // 컴파일 오류!
    //Exam0230_X.im(); // 컴파일 오류!

    final Exam0230_X obj = new Exam0230_X();
    obj.iValue = 100; // OK!
    obj.im(); // OK!

    // 다른 클래스에 있는 inner class의 레퍼런스를 선언하려면 해당 클래스를 지정해야 한다.
    // 선언하는 방법은 static nested class와 같다.
    // "바깥 클래스명.inner class명"
    Exam0230_X.A a;

    // inner class도 마찬가지로 바깥 클래스의 인스턴스를 통해서만 객체를 생성할 수 있다.
    a = obj.new A();
    // 위 코드는 컴파일러가 다음과 같이 바꾼다.
    // new A(obj);
    // 바깥 클래스의 객체 주소인 obj를 생성자에 넘기는 코드로 바꾼다.

    // 주의!
    // static nested class와 달리 inner class 객체를 생성할 때
    // 바깥 클래스명으로 생성할 수 없다.
    //a = new Exam0230_X.A(); // 컴파일 오류!
  }
}