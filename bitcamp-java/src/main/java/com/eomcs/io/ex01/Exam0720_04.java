// 지정한 폴더 삭제하기 (응용)
package com.eomcs.io.ex01;

import java.io.File;

public class Exam0720_04 {
  public static void main(String[] args) throws Exception {
    File dir = new File("temp");

    deleteFile(dir);
    System.out.println("삭제하였습니다!");
  }

  static void deleteFile(File dir) {
    File[] files = dir.listFiles();

    for (File file : files) {
      if (file.isDirectory()) {
        deleteFile(file); // 하위 디렉토리 삭제. file 객체의 정보를 넘김
      } else {
        file.delete(); // 하위 파일 삭제
      }
    }
  }
}