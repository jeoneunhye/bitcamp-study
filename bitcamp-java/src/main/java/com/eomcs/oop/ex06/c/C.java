package com.eomcs.oop.ex06.c;

public class C {
  private void m1() {}
  // private 접근 범위:
  // => 현재 클래스
  
  void m2() {}
  // (default) 접근 범위:
  // => 현재 클래스 + 같은 패키지 소속 클래스
  
  protected void m3() {}
  // protected 접근 범위:
  // => 현재 클래스 + 같은 패키지 소속 클래스 + 서브 클래스
  
  public void m4() {}
  // public 접근 범위:
  // => 모두
}