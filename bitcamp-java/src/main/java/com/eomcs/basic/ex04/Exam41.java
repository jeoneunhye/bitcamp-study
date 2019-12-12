package com.eomcs.basic.ex04;

public class Exam41 {
public static void main(String[] args) {
  // 문자 변수
  short s;
  char c;

  s = -32768;
  s = 32767;

  c = 0;
  c = 65535;  // 넘어가면 int를 왜 char에 담냐고 컴파일 오류

  char c1 = 65;
  char c2 = 0x41;
  char c3 = 0b0100_0001;
  char c4 = 'A'; // 저장될 때에는 A라는 문자에 부여된 ★UCS-2 코드로 리턴하여 c4에 저장됨

  System.out.println(c1); // A
  System.out.println(c2); // A
  System.out.println(c3); // A char 안에 넣은 값이기 때문에 정수가 아닌 문자가 출력됨
  System.out.println(c4); // A

  //int c1 = 65;
  //int c2 = 0x41;
  //int c3 = 0b0100_0001;
  //System.out.println(c1); // 65
  //System.out.println(c2); // 65
  //System.out.println(c3); // 65

  int i1 = 'A'; // 컴파일 ok
  i1 = i1 + 10;
  System.out.println(i1); // 75
  // 형변환 - 정수 값을 문자 코드로 판단해서 코드값에 해당하는 문자 출력해줘
  System.out.println((char)i1); // K

  for (int i = 65; i < 'A' + 26; i++) { // 알파벳 26개
    System.out.print((char)i);  // ABCDEFGHIJKLMNOPQRSTUVWXYZ

  // 문자는 내부적으로 숫자로 다루어진다.
  }
  }
}