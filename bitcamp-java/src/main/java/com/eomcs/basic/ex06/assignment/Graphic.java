package com.eomcs.basic.ex06.assignment;
// 직접 사용하는 클래스가 아님!
public class Graphic {
  static void drawLine(int length) {        // 길이만 줘
    int x = 0;
    while (x++ < length) {
      System.out.print("*");
    }
  }
  
  static void drawLine(int length, char ch) {    // 길이 +char 출력할 문자를 받음
    int x = 0;
    while (x++ < length) {
      System.out.print(ch);
    }
  }
  //**같은 기능을 수행하는 메서드에 대해 같은 이름을 갖도록 문법적 허용: 오버로딩 (자바만 됨)
}
