// 인스턴스 초기화 블록과 필드 초기화, 생성자의 실행 순서
package com.eomcs.oop.ex03;

public class Exam0631 {

  static class A {
    A() {
      // 자파 컴파일러는 인스턴스 초기화 블록이나 필드 초기화 문장이 있다면,
      // 종료에 구분없이 선언된 순서 그대로 모든 생성자의 처음 부분에 복사한다.
      // 즉 다음과 같다.
      // ★인스턴스 초기화 블록과 필드 초기화 문장의 실행 순서는 문장의 순서를 따른다.

      //      a = 200;
      //      System.out.println("초기화 블록");
      //      a = 100;

      System.out.println("A()");
      this.a = 300; // 주석 막으면 100
    }

    A(int a) {
      //      a = 200;
      //      System.out.println("초기화 블록");
      //      a = 100;
      System.out.println("A(int)");
      this.a = a;
    }

    // 인스턴스 초기화 블록(initializer block) 
    {
      this.a = 200;
      System.out.println("초기화 블록");
    }

    // 필드 초기화 문장(variable initializer)
    int a = 100;
  }

  public static void main(String[] args) {
    A obj1 = new A();
    System.out.println(obj1.a);
    
    System.out.println("-----------");
    
    A obj2 = new A(1111);
    System.out.println(obj2.a);
  }
}