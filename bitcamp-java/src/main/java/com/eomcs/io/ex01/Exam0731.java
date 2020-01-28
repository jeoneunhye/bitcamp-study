// 클래스 파일 이름을 출력할 때 패키지 이름을 포함하기 (응용)
package com.eomcs.io.ex01;

import java.io.File;
import java.io.FileFilter;

public class Exam0731 {
  public static void main(String[] args) throws Exception {
    File dir = new File("bin/main");
    System.out.println(dir.getCanonicalPath()); // bitcamp-java\bin\main

    printList(dir, "");
  }

  static void printList(File dir, String packageName) {
    File[] files = dir.listFiles(new FileFilter() {
      @Override
      public boolean accept(File pathname) {
        if (pathname.isHidden())
          return false;

        if (pathname.getName().contains("$")) {
          return false;
        }

        if (pathname.isDirectory() || (pathname.isFile() && pathname.getName().endsWith(".class"))) {
          return true;
        }
        return false;
      }
    });

    if (packageName.length() > 0) { // 패키지가 있는 클래스의, 패키지명 뒤에만 .이 붙도록
      packageName += ".";
    }
    for (File file : files) {
      if (file.isHidden()) {
        continue;
      }
      if (file.isDirectory()) { // 디렉토리면
        printList(file, packageName + file.getName()); // 패키지 포함한 클래스명 출력
      } else { // 파일이면
        System.out.println(packageName + file.getName().replace(".class", ""));
      }
    }
  }
}