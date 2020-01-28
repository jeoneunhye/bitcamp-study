// 지정한 폴더 삭제하기 (★응용)
package com.eomcs.io.ex01;

import java.io.File;

public class Exam0720 {
  public static void main(String[] args) throws Exception {
    File dir = new File("temp");

    deleteFile(dir);
    System.out.println("삭제하였습니다!");
  }

  static void deleteFile(File dir) {
    File[] files = dir.listFiles();

    for (File file : files) {
      if (file.isDirectory()) { // 하위 폴더면
        deleteFile(file);
        // 하위 디렉토리 삭제. file 객체의 정보를 넘김
        // 재귀호출 => 메서드 안에서 도는게 아니다.
        // stack처럼 메모리에 쌓인다. 별도의 메서드라고 생각해라.
        // listFiles() 하위 정보가 담긴 배열 만든 후 하위 폴더면 if문 실행, 하위 파일이면 else문 실행
      } else {
        file.delete(); // 하위 파일 삭제
      }
    }
    dir.delete(); // temp 폴더 삭제
  }
}