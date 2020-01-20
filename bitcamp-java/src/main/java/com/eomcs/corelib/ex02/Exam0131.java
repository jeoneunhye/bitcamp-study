// String - mutable vs immutable 객체
package com.eomcs.corelib.ex02;

public class Exam0131 {
  public static void main(String[] args) {
    // StringBuffer는 mutable 객체다.
    // 인스턴스의 데이터를 변경할 수 있다.
    // 원래의 문자열을 변경하고 싶을 때 사용하는 클래스다.
    StringBuffer buf = new StringBuffer("Hello"); // 0부터 시작
    buf.replace(2, 4, "xxxx"); // 2이상 4미만 문자 삽입(대체)
    System.out.println(buf); // 원본을 바꾼다. 결과 Hexxxxo
  }
}