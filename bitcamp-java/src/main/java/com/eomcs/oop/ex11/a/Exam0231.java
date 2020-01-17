// non-static nested class = inner class의 생성자
package com.eomcs.oop.ex11.a;

class Exam0231_X {
  int iValue;
  void im() {}

  class A {
    String name;
    int age;

    public A(final String name, final int age) { // 파라미터를 받는 생성자를 생성하였다.
      this.name = name;
      this.age = age;
    }
    // 컴파일러가 바깥 클래스를 받을 필드 및 생성자 자동 생성
    /* ex)
    Exam0231_X this;

    public A(Exam0231_X this, final String name, final int age) {
      this.this = this;
      this.name = name;
      this.age = age;
      }
      기본 생성자, 파라미터를 받는 생성자 두 개를 만드는 것이 아니다!
      하나의 생성자에 합쳐진다.
     */
    void m1() {

    }
  }
}

public class Exam0231 {
  public static void main(final String[] args) {
    final Exam0231_X obj = new Exam0231_X();

    obj.new A("홍길동", 20);
    // 위 코드는 컴파일러가 다음과 같이 바꾼다.
    // new A(obj, "홍길동", 20);
    // 바깥 클래스의 객체 주소인 obj
    // 바깥 클래스의 객체 주소를 받기 위해 '컴파일러가 변경한' 생성자를 호출한다.
  }
}