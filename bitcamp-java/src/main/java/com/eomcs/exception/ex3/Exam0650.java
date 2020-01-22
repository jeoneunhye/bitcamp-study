// 예외 처리 후 마무리 작업 - try-with-resources
package com.eomcs.exception.ex3;

public class Exam0650 {
  static class A {}

  static class B implements AutoCloseable {
    @Override
    public void close() throws Exception {
      System.out.println("B 클래스의 자원을 해제하였습니다.");
    }
  }

  public static void main(String[] args) throws Exception {
    B obj = null;
    try (
        obj = new B(); // 컴파일 오류! 변수 선언은 반드시 소괄호 안에서 해야 한다.
        ) {
      System.out.println("try 블록 실행...");
    }
  }
}