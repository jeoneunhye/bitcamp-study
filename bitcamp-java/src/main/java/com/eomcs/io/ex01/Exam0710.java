// 지정한 폴더 및 그 하위 폴더까지 모두 검색하여 파일 및 디렉토리 이름을 출력하기 (★응용)
package com.eomcs.io.ex01;

import java.io.File;

public class Exam0710 {
  public static void main(String[] args) throws Exception {
    // 결과 예)
    // /Users/bitcamp/git/test
    // src/
    //   main/
    //     java/
    //       com/
    //         Hello.java
    //         Hello2.java
    // build.gradle
    // settings.gradle
    // ...

    File dir = new File(".");
    System.out.println(dir.getCanonicalPath()); // 계산된 절대 경로

    printList(dir, 1);
  }

  static void printList(File dir, int level) {
    // listFiles() 현재 디렉토리의 하위 파일 및 디렉토리 목록을 알아낸다.
    File[] files = dir.listFiles();

    // 리턴받은 파일 배열에서 이름을 꺼내 출력한다.
    for (File file : files) {
      printIndent(level);

      if (file.isDirectory() && !file.isHidden()) {
        System.out.printf("%s/\n", file.getName()); // 하위 디렉토리 출력
        printList(file, level + 1); // 하위 파일 출력. 들여쓰기 한 번 더, 증감연산자 X!
      } else {
        System.out.print("|-- ");
        System.out.printf("%s\n", file.getName());
      }
    }
  }

  static void printIndent(int level) { // 들여쓰기
    for (int i = 0; i < level; i++) {
      System.out.print("  ");
    }
  }
}