// 익명 클래스로 FileFilter 만들기
package com.eomcs.io.ex01;

import java.io.File;
import java.io.FileFilter;

public class Exam0630 {
  public static void main(String[] args) throws Exception {
    // 필터 객체를 한 개만 만들 거라면
    // 익명 클래스로 정의하는 것이 낫다.
    FileFilter filter = new FileFilter() {
      @Override
      public boolean accept(File file) {
        if (file.isFile() && file.getName().endsWith(".java"))
          return true;
        return false;
      }
    };

    File dir = new File(".");

    // => 확장자가 .java인 파일의 이름만 추출하기
    // 1) 필터 준비 !=> 위에서 이미 만듦
    // JavaFilter javaFilter = new JavaFilter();

    // 2) 필터를 사용하여 디렉토리의 목록을 가져오기
    //File[] files = dir.listFiles(javaFilter); 아래 문장으로 변경
    File[] files = dir.listFiles(filter);

    for (File file : files) {
      System.out.printf("%s %12d %s\n", file.isDirectory() ? "d" : "-",
          file.length(), file.getName());
    }
  }
}