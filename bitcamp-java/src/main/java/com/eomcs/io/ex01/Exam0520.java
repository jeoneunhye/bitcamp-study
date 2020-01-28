// 디렉토리에 들어 있는 파일이나 하위 디렉토리 정보 알아내기 II
package com.eomcs.io.ex01;

import java.io.File;

public class Exam0520 {
  public static void main(String[] args) throws Exception {
    File dir = new File(".");

    // => 파일이나 하위 디렉토리 정보를 File 객체로 받기
    File[] files = dir.listFiles(); // 'File 객체'의 배열을 리턴하는 메서드 listFiles()

    for (File file : files) {
      System.out.printf("%s %12d %s\n", file.isDirectory() ? "d" : "-",
          // 조건 연산자 폴더면 d, 폴더가 아니면 -
          file.length(), file.getName());
    }
  }
}