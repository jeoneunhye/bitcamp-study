package com.eomcs.basic.ex03;

// 부동 소수점 리터럴 - 메모리 크기
//
public class Exam32 {
  public static void main(String[] args) {
    System.out.println(3.141592653589793);  // 8바이트 부동소수점
    System.out.println(3.141592653589793D); // 대문자 D(double)
    System.out.println(3.141592653589793d);

    System.out.println(3.141592653589793f);  // 4바이트 부동소수점
    System.out.println(3.141592653589793F); // 소문자 f 결과값 3.1415927
    //정수는 작은 메모리에 큰 값을 집어넣으면 컴파일 오류가 뜨는데 소수는 값이 짤려서 들어갈 뿐이라 위험하다
    //개발자가 잘못된 값을 넣었다는 것을 인지하지 못한다.
  }
}