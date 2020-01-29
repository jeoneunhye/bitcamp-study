// ★디렉토리에 들어 있는 파일이나 하위 디렉토리 정보 알아내기 I
package com.eomcs.io.ex01;

import java.io.File;

public class Exam0510 {
  public static void main(String[] args) throws Exception {
    // 현재 폴더의 정보를 준비한다.
    File dir = new File(".");

    // 현재 폴더에 있는 파일이나 하위 디렉토리 이름을 알아내기
    String[] names = dir.list(); // 리턴 값이 String

    for (String name : names) {
      System.out.println(name);
    }
  }
}