// 버퍼 사용 전 - 데이터 읽는데 걸린 시간 측정
package com.eomcs.io.ex06;

import java.io.FileInputStream;

public class Exam0110 {
  public static void main(String[] args) throws Exception {
    FileInputStream in = new FileInputStream("temp/jls11.pdf"); // 파일 크기 3,259kb
    // https://docs.oracle.com/javase/specs/

    int b;

    long startTime = System.currentTimeMillis(); // 현재 시점의 시간 밀리초로 리턴

    int callCount = 0;
    while ((b = in.read()) != -1) { // 파일을 끝까지 읽는다.
      callCount++; // read()를 호출한 횟수 기록
    }
    // 아래와 같은 문장이다.
    /*
    while (true) {
    b = in.read();
    if (b == -1) {
    break;
    }
    callCount++;
    }
     */

    long endTime = System.currentTimeMillis();

    System.out.println(endTime - startTime); // 8449
    System.out.println(callCount); // 3336294

    in.close();
  }
}