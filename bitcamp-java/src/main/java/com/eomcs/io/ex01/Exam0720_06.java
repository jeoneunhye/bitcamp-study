// 지정한 폴더 삭제하기 (★응용)
package com.eomcs.io.ex01;

import java.io.File;

public class Exam0720_06 {
  public static void main(String[] args) throws Exception {
    File dir = new File("temp");

    deleteFile(dir);
    System.out.println("삭제하였습니다!");
  }

  static void deleteFile(File dir) {
    if (dir.isDirectory()) {
      File[] files = dir.listFiles();
      for (File file : files) {
        deleteFile(file); // 하위 디렉토리 및 파일 삭제
      }
    }
    dir.delete(); // temp 폴더 삭제
  }
}