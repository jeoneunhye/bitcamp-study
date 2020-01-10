// 제네릭(Generic) 문법 정리- 제네릭 파라미터
package com.eomcs.generic.ex01;

import java.util.ArrayList;
import java.util.HashSet;

public class Exam0224 {
  static class A {}
  static class B1 extends A {}
  static class B2 extends A {}
  static class C extends B1 {}
  
  public static void main(String[] args) {
    // m1(ArrayList<? extends B1>)
    // B1 타입 및 그 하위 타입에 대해서 ArrayList 객체를 파라미터로 넘길 수 있다.
    //m1(new ArrayList<Object>()); // 컴파일 오류
    //m1(new ArrayList<A>());
    m1(new ArrayList<B1>()); // 컴파일 오류
    //m1(new ArrayList<B2>()); // 컴파일 오류
    m1(new ArrayList<C>()); // 컴파일 오류
  }
  
  static void m1(ArrayList<? extends B1> list) { // B1 + B1의 모든 서브 클래스
    
  }
}