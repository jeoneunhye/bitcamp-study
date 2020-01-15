// 다중 인터페이스 구현
package com.eomcs.oop.ex09.c;
// 클래스는 여러 개의 규칙을 이행할 수 있다.
public class Exam02 implements B, C {
  public void m2() {} // B 인터페이스 뿐만 아니라
  public void m1() {} // B의 수퍼 인터페이스 A의 메서드까지 구현해야 한다.
  public void m3() {} // C의 인터페이스 구현

  public static void main(String[] args) {
    // 한 클래스가 여러 개의 인터페이스(사용 규칙)를 구현했다면
    // 각 인터페이스로 구분해서 그 객체를 사용할 수 있다.
    Exam02 obj = new Exam02();

    // B 인터페이스 레퍼런스에 담는다면 B 규칙에 따라 사용할 수 있고
    // 'Exam02 객체를 B 용도로 사용해볼까?'
    B obj2 = obj;
    obj2.m2(); // B.m2()
    obj2.m1(); // A.m1() <--- A를 상속받은 B 인터페이스
    // obj2.m3(); // 컴파일 오류

    // C 인터페이스 레퍼런스에 담는다면 C 규칙에 따라 사용할 수 있다.
    // 'Exam02 객체를 C 용도로 사용해볼까?'
    C obj3 = obj;
    obj3.m3(); // C.m3()
    // obj3.m1(); // 컴파일 오류
    // obj3.m2(); // 컴파일 오류
  }
}