// 디렉토리에 들어있는 파일(디렉토리) 목록을 꺼낼 때 필터 적용하기 I
package com.eomcs.io.ex01;

import java.io.File;
import java.io.FilenameFilter;

public class Exam0610 {
  public static void main(String[] args) throws Exception {
    class JavaFilter implements FilenameFilter {
      // main() 안에 들어간 로컬 클래스는 static을 붙이지 않는다.
      @Override
      public boolean accept(File dir, String name) {
        // 상위 디렉토리 정보 dir와 파일 또는 디렉토리 이름 name을 파라미터로 받는다.
        // accept()는 list()에서 호출한다.
        // 해당 폴더에 들어 있는 파일이나 디렉토리를 찾을 때마다 호출한다.
        // 하위 폴더 아래는 뒤지지 않는다.
        // 이 메서드에서 해야할 일은 찾은 파일이나 디렉토리를
        // 리턴할 배열에 포함시킬지 여부다.
        // true를 리턴하면 배열에 포함되고
        // false를 리턴하면 배열에 포함되지 않는다.

        // 파일 또는 디렉토리 이름이 .java로 끝나는 경우에만 리턴 배열에 포함시킨다.
        if (name.endsWith(".java"))
          return true; // 조회 결과에 포함시켜라!
        return false; // 조회 결과에서 제외하라!
      }
    }

    File dir = new File("."); // 현재 폴더를 의미

    // => 확장자가 .java인 파일의 이름만 추출하기
    // 1) 필터 준비
    JavaFilter javaFilter = new JavaFilter();

    // 2) 필터를 사용하여 디렉토리의 목록을 가져오기
    String[] names = dir.list(javaFilter); // list()는 인터페이스를 구현한 객체를 파라미터로 받는다.

    for (String name : names) {
      System.out.println(name);
    }

    // 문제점:
    // temp.java는 디렉토리다.
    // 현재의 필터는 파일 이름으로만 검사한다.
    // 파일인지 디렉토리인지 여부는 검사하지 않는다.
    // 해결책?
    // Exam0611.java를 보라!
  }
}