// local class와 .class 파일명
package com.eomcs.oop.ex11.a;

public class Exam0320 {
  // 로컬 클래스를 컴파일하면 다음 규칙에 따라 .class 파일의 이름이 결정된다.
  // => [바깥 클래스명][$번호][로컬 클래스명].class
  // ex) Exam0310$1A.class
  // $ 다음에 오는 번호는 로컬 클래스가 등장한 번호다.
  // 로컬 클래스가 어떤 메서드 안에 정의된 것인지는 구분하지 않는다.
  // 1번부터 시작해서 같은 이름의 클래스가 또 등장하면 2번으로 번호가 증가한다.
  void m1() {
    class A { // Exam0320$1A.class
    }

    class B { // Exam0320$1B.class
    }

    new A();
    new B();
  }

  void m2() {
    class A { // Exam0320$2A.class
    }
    class X { // Exam0320$1X.class
    }

    new A();
    new X();
  }
}