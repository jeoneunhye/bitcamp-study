package com.eomcs.algorithm.data_structure.stack;

public class StackTest {
  public static void main(String[] args) {
    Stack s = new Stack();

    s.push("aaa");
    s.push("bbb");
    s.push("ccc");
    s.push("ddd");
    // 상수풀에 문자열(String 인스턴스)이 저장되고 해당 주소가 레퍼런스 변수 안에 들어간다.

    while (!s.empty()) { // 값이 비어있지 않다면(size != 0이라면)
      System.out.println(s.pop()); // 맨 끝부터 꺼내 출력
    }
  }
}