// 지정한 폴더 삭제하기 (응용)
package com.eomcs.io.ex01;

import java.io.File;

public class Exam0720_02 {
  public static void main(String[] args) throws Exception {
    File dir = new File("temp");

    // 삭제하는 메서드 생성
    deleteFile(dir);
  }

  static void deleteFile(File dir) {

  }
}