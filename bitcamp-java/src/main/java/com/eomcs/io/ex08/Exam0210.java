// 플러그인으로 전환한 BufferedInputStream 사용하기 - 읽기 시간 측정
package com.eomcs.io.ex08;

import java.io.FileInputStream;

public class Exam0210 {
  public static void main(String[] args) throws Exception {
    FileInputStream fileIn = new FileInputStream("temp/jls11.pdf");
    BufferedInputStream in = new BufferedInputStream(fileIn);

    int b;

    long startTime = System.currentTimeMillis();

    int callCount = 0;
    while ((b = in.read()) != -1) { // 파일을 끝까지 읽는다.
      callCount++;
    }

    long endTime = System.currentTimeMillis();

    System.out.println(endTime - startTime); // 48
    System.out.println(callCount); // 3336294

    in.close();
  }
}