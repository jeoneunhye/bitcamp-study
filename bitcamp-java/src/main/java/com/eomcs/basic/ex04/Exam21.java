package com.eomcs.basic.ex04;

public class Exam21 {
public static void main(String[] args) {
  // 정수 변수 - 메모리 크기
  byte b; // 0000 0000 ~ 1111 1111 = 00~ff = -128 ~ 127
  b = -128;
  b = 127;

  //b = -129, 128; 컴파일 오류 : possible lossy conversion from int to byte

  short s;  // -32768 ~ 32767
  s = -32768;
  s = 32767;

  //s = -32769, 32768; 컴파일 오류

  int i;  // 약 -21억 ~ 21억. 제일 많이 사용
  i = -21_0000_0000;
  i = 21_0000_0000;

  //i = -22_0000_0000; 컴파일 오류 : integer number too large

  long l; // 약 922경 ~ 922경
  l = -922_0000_0000_0000_0000L;
  l = 922_0000_0000_0000_0000L;

  //l = -923_0000_0000_0000_0000L; 컴파일 오류 : integer number too large
  }
}