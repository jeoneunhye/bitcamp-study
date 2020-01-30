// 버퍼 사용 후 - 데이터 읽는데 걸린 시간 측정
package com.eomcs.io.ex06;

import java.io.FileInputStream;

public class Exam0120 {
  public static void main(String[] args) throws Exception {
    FileInputStream in = new FileInputStream("temp/jls11.pdf");

    byte[] buf = new byte[8192];
    // Exam0220.java 주석 참고
    // 메모리를 많이 준비해 두면 데이터를 읽는 시간이 단축된다.
    // 그러나 Web Browser 같은 경우 동접자가 많고 준비되는 메모리가 과도하게 많아지면
    // RAM이 과부하되어 속도가 기대만큼 빨라지지 않는다. 서버 관리에 비효율적
    // 보통 1024B 단위 1 ~ 8KB 정도 메모리를 준비한다. (trade off 필요)
    int len = 0;

    long startTime = System.currentTimeMillis();

    int callCount = 0;
    while ((len = in.read(buf)) != -1) { // 파일을 끝까지 읽는다.
      callCount++; // read()를 호출한 횟수 기록
    }

    long endTime = System.currentTimeMillis();

    System.out.println(endTime - startTime); // 4
    System.out.println(callCount); // 408
    in.close();
  }
}