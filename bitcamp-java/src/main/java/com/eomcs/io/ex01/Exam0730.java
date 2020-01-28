// 지정한 폴더에서 .class 파일만 찾아 출력하기 (응용)
package com.eomcs.io.ex01;

import java.io.File;
import java.io.FileFilter;

public class Exam0730 {
  public static void main(String[] args) throws Exception {
    File dir = new File("bin/main");
    System.out.println(dir.getCanonicalPath()); // 계산된 절대 경로 bitcamp-java\bin\main

    printList(dir);
  }

  static void printList(File dir) {
    File[] files = dir.listFiles(new FileFilter() {
      @Override
      public boolean accept(File pathname) {
        if (pathname.isHidden())
          return false; // 숨김파일 제외

        if (pathname.getName().contains("$")) {
          return false; // 중첩클래스 제외
        }

        if (pathname.isDirectory() || (pathname.isFile() && pathname.getName().endsWith(".class"))) {
          return true;
        }
        return false;
      }
    });

    for (File file : files) {
      if (file.isHidden()) {
        continue;
      }
      if (file.isDirectory()) {
        //System.out.printf("%s/\n", file.getName());
        printList(file); // 디렉토리 출력x 파일인 경우만
      } else {
        System.out.printf("%s\n", file.getName());
      }
    }
  }
}