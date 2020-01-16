// 클래스의 종류 : 중첩 클래스 요약 정리
package com.eomcs.oop.ex11.b;

import java.io.File;
import java.io.FilenameFilter;
// 패키지 멤버 클래스
class A {}

// 패키지 멤버 클래스
public class Exam0170 {
  // static nested class
  static class B {}

  // non-static nested class = inner class
  class C {}

  public static void main(String[] args) throws Exception {
    // local class 
    class D {}

    // anonymous class
    Object obj = new Object() {};
    
    Object ob2 = new Object() {
      int a; // 인스턴스 필드 선언 가능
      
      @Override
      public String toString() { // 메서드 오버라이딩 가능
        return "오호라! 익명 클래스";
      }
    };
  }
}