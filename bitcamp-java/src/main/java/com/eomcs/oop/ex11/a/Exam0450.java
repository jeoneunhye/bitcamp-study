// anonymous class - 수퍼 클래스의 메서드 오버라이딩하기
package com.eomcs.oop.ex11.a;

public class Exam0450 {
  static class A {
    String name;

    public A() {
      this.name = "이름 없음";
    }

    public A(final String name) {
      this.name = name;
    }

    public void print() {
      System.out.println(name);
    }
  }

  public static void main(final String[] args) {
    // 익명 클래스를 정의할 때 호출할 수퍼 클래스의 생성자를 지정할 수 있다.
    // 문법:
    // new 수퍼클래스명(파라미터, ...) {}
    // 즉 생성자에 넘겨주는 파라미터로 호출될 생성자를 지정한다.
    A obj = new A("유관순") {
      @Override
      public void print() {
        System.out.printf("'%s'님 반갑습니다!", name);
      }
    };

    obj.print();
  }
}