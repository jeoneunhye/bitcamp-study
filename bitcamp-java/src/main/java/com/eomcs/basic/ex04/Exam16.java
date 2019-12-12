package com.eomcs.basic.ex04;

public class Exam16 {
public static void main(String[] args) {
  // 변수의 종류
  //  => primitive data type(자바 원시 데이터 타입)
  byte b; // 정수 1바이트 크기를 갖는 메모리
  short s;  //2
  int i;  //4
  long l; //8

  float f;  //부동소수점 4
  double d; //8

  boolean bool; // jvm에서 int로 취급(4)

  char c; //2

  //  => reference : 다른 메모리의 주소를 저장하는 변수
  String str; //문자열
  java.sql.Date date; //날짜
  Thread t; //쓰레드
  java.net.Socket socket; //네트워크 연결 정보
  int[] arr;
  //java.lang에 있지 않은 단어들은 패키지를 지정해줘야 한다.
  }
}