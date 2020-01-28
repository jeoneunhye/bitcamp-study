// 파일 정보 조회 - java.io.File 클래스
package com.eomcs.io.ex01;

import java.io.File;

public class Exam0210 {
  public static void main(String[] args) throws Exception {
    // File 클래스
    // => 파일이나 디렉토리 정보를 관리
    // => 파일이나 디렉토리를 생성, 삭제, 변경

    // 현재 파일 정보 조회
    File file = new File("./build.gradle"); // 현재 폴더 밑에 build.gradle 파일 (./ 생략 가능)
    System.out.printf("파일명: %s\n", file.getName());
    System.out.printf("★파일크기: %d\n", file.length());
    System.out.printf("경로: %s\n", file.getPath());
    System.out.printf("절대경로: %s\n", file.getAbsolutePath());
    System.out.printf("계산된 절대경로: %s\n", file.getCanonicalPath());

    // HDD 용량이 나온다.
    System.out.printf("총크기: %d\n", file.getTotalSpace());
    System.out.printf("남은크기: %d\n", file.getFreeSpace());
    System.out.printf("가용크기: %d\n", file.getUsableSpace());

    System.out.printf("디렉토리여부: %b\n", file.isDirectory());
    System.out.printf("파일여부: %b\n", file.isFile());
    System.out.printf("감춤여부: %b\n", file.isHidden());
    System.out.printf("존재여부: %b\n", file.exists());
    System.out.printf("실행가능여부: %b\n", file.canExecute());
  }
}