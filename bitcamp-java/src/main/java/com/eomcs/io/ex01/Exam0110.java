// 폴더 정보 조회 - java.io.File 클래스
package com.eomcs.io.ex01;

import java.io.File;

public class Exam0110 {
  public static void main(String[] args) throws Exception {
    // File 클래스
    // => 파일이나 디렉토리 정보를 관리
    // => 파일이나 디렉토리를 생성, 삭제, 변경

    // - 현재 폴더 정보 조회
    // "."으로 경로를 표시한다.
    // 이클립스에서 프로그램을 실행한다면 .은 프로젝트 폴더를 가리킨다.
    // 콘솔에서 프로그램을 실행한다면 .은 현재 명령어를 실행하는 위치를 가리킨다.
    File currentDir = new File(".");
    System.out.printf("폴더명: %s\n", currentDir.getName());
    // 인스턴스 주소로 찾아가 파일명을 리턴해주는 '메서드'이자
    // 데이터로 연산을 하는 '연산자' 또는 '메세지' getName()
    System.out.printf("경로: %s\n", currentDir.getPath());
    System.out.printf("★절대경로: %s\n", currentDir.getAbsolutePath());
    System.out.printf("계산된 절대경로: %s\n", currentDir.getCanonicalPath());

    // HDD 용량이 나온다.
    System.out.printf("총크기: %d\n", currentDir.getTotalSpace());
    System.out.printf("★남은크기: %d\n", currentDir.getFreeSpace());
    System.out.printf("★가용크기: %d\n", currentDir.getUsableSpace());
    // 가용크기: 운영체제, JVM이 차지하는 용량 제외한 크기

    System.out.printf("디렉토리여부: %b\n", currentDir.isDirectory());
    System.out.printf("파일여부: %b\n", currentDir.isFile());
    System.out.printf("감춤여부: %b\n", currentDir.isHidden());
    System.out.printf("존재여부: %b\n", currentDir.exists());
    System.out.printf("실행가능여부: %b\n", currentDir.canExecute());
    // ex) 폴더: 실행 불가, .exe 파일: 실행 가능
  }
}