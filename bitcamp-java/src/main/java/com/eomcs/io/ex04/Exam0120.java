// 데이터 읽기 - int값 읽기
package com.eomcs.io.ex04;

import java.io.FileInputStream;

public class Exam0120 {
  public static void main(String[] args) throws Exception {
    FileInputStream in = new FileInputStream("temp/test3.data");

    // Exam0110을 실행하여 출력한 데이터를 read()로 읽는다.
    // read()는 1바이트를 읽어 int값 00 00 00 d2로 만든 후 리턴한다.
    int value = in.read(); // 실제 리턴한 값은 0xd2다.

    in.close();

    System.out.printf("%1$x(%1$d)\n", value); // d2(210)
  }
}