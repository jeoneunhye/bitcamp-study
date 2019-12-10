package com.eomcs.basic.ex03;

// 이스케이프 문자(escape character)

public class Exam7 {
  public static void main(String[] args) {
    // 문자를 제어하는 기능을 가진 문자
    // 문법
    //  \ [n|r|f|t|b|'|"|\]
    System.out.println("Hello, world!");
    System.out.println("Hello,\nworld!"); // 줄바꿈 \ n
    // 결과값
    // Hello
    // world!
    System.out.println("Hello,\rabc"); // 커서를 처음으로 돌림 \ r
    // 결과값 abclo,
    // cursor란? 문자를 출력할 위치를 가리키는 것
    System.out.println("Hello,\b\b\bworld!"); // 커서를 뒤로 한 칸 이동시킴
    // 결과값 Helworld!
    System.out.println("Hello,\tworld!"); // 탭 공간을 추가
    // 결과값 Hello,  world!
    System.out.println("Hello,\fworld!"); // formfeed 추가 (인쇄 관련)
    System.out.println("Hello,\"w\"orld!"); // \"(더블 코테이션) 추가
    System.out.println("Hello,'w'orld!"); // '(싱글 코테이션) 추가 ""안에서 '문자는 그냥 적는다
    System.out.println('\'');  // ''안에서 ' 문자를 출력할 때는 앞에 \가 필요
    System.out.println('"');  // ''안에서 " 문자는 그냥 적는다
    System.out.println("c:\\Users\\user\\git");  // 디렉토리 경로
    // \ 문자를 출력시키는 문자 하나 더 적기
    }
}

// (참고)줄바꿈 코드
// Carrage Return(CR) : 0d  타자기에서 줄바꿈을 위해 움직이는 기계 이름이 carrage
// Line Feed(LF) : 0a

// windows OS에서는 줄바꿈을 표시하기 위해 CRLF 2바이트 코드를 삽입한다. sublime 편집기 hex viewer로 보면 보임 (엔터칠때마다 2바이트씩)
// Unix OS에서는 줄바꿈을 표시하기 위해 LF 1바이트 코드를 삽입한다. (0a만 삽입됨-1바이트)
// 이클립스에서 os환경 설정이 가능하다