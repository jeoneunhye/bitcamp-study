// 지정한 폴더 삭제하기 (응용)
package com.eomcs.io.ex01;

import java.io.File;

public class Exam0720_03 {
  public static void main(String[] args) throws Exception {
    File dir = new File("temp");

    deleteFile(dir);
  }

  static void deleteFile(File dir) {
    // temp 폴더의 하위 디렉토리 및 파일을 배열에 담음
    File[] files = dir.listFiles();
  }
}