package com.eomcs.basic.ex99;

public class Exam11 {
public static void main(String[] args) {
  // 값 출력하기
  // println() 사용법
  // => 출력 + 줄바꿈
  System.out.println(100);
  System.out.println(3.14);
  System.out.println(true);
  System.out.println('가');
  System.out.println("안녕!");

  System.out.println();

  // print() 사용법
  //  => 출력
  System.out.print(100);
  System.out.print(3.14);
  System.out.print(true);
  System.out.print('가');
  System.out.print("안녕!");  // 10034.14true가안녕!

  System.out.print("OK!");

  System.out.print('\n');

  System.out.print("OK!\n");  // 줄바꿈이 가능
  System.out.print("NO!");

  // printf() 사용법
  //  => printf("형식", 값1, 값2, ...);
  System.out.printf("\n이름: %s", "홍길동");  // %s 문자
  System.out.printf("\n나이: %d", 20);  // %d 숫자

  System.out.printf("\n이름(나이): %s(%d)", "홍길동", 20);

  // 정수 삽입
  System.out.printf("\n %d, %x, %c", 65, 65, 65);
  // %d: decimal
  // %x: hexadecimal
  // %c: character

  // 삽입될 값 지정하기
  // => %값위치$d
  //System.out.printf("\n %d, %x, %c", 65); 컴파일 오류
  System.out.printf("\n %1$d, %1$x, %1$c", 65);

  // 논리값 삽입
  System.out.printf("\n재직자: %s, %b", true, true);  // 재직자: true, true
  System.out.printf("\n나이: %s, %d", 20, 20);  // 나이: 20, 20 (s에는 다 들어감)

  // 출력할 공간 확보
  System.out.printf("\n이름: [%s]", "홍길동");  // 이름: [홍길동]
  System.out.printf("\n이름: [%20s]", "홍길동");  // 이름: [                 홍길동] (오른쪽 정렬)
  System.out.printf("\n이름: [%-20s]", "홍길동"); // 이름: [홍길동                 ] (왼쪽 정렬)
  System.out.printf("\n숫자: [%d]", 12345); // 숫자: [12345]
  System.out.printf("\n숫자: [%10d]", 12345); // 숫자: [     12345]
  System.out.printf("\n숫자: [%-10d]", 12345);  // 숫자: [12345     ]
  System.out.printf("\n숫자: [%010d]", 12345);  // 숫자: [0000012345] (빈 자리가 0으로 채워짐)
  System.out.printf("\n숫자: [%+010d], [%+010d]", 12345, -12345); // 숫자: [+000012345], [-000012345]

  // ★일시 다루기
  java.util.Date today = new java.util.Date();  // 실행 시점의 날짜와 시간
  // today.setDate(9);

  //System.out.printf("\n%s", today); // Thu Dec 12 12:04:06 KST 2019
  // 날짜 객체에서 년,월,일,요일,시,분,초 추출하기
  System.out.printf("\n%tY", today);  // 2019
  System.out.printf("\n%ty", today); // 19
  System.out.printf("\n%tB, %tb", today, today);  // 12월, 12월 (영어면 December, Dec)
  System.out.printf("\n%tm", today);  // 12 ('월'이 빠짐)
  System.out.printf("\n%td, %te", today, today);  // 12, 12 (한자리 숫자면 09, 9)
  System.out.printf("\n%tA, %ta", today, today);  // 목요일, 목 (영어면 Sunday, Sun)
  System.out.printf("\n%tH, %tI", today, today);  // 24시간 기준/ 12시간 기준
  System.out.printf("\n%tM", today);  // 18
  System.out.printf("\n%tS, %tL, %tN", today, today, today);  // 52, 196, 196000000 초, 밀리초, 나노초
  System.out.printf("\n%tp, %Tp", today, today);  // 오후, 오후 (영어면 pm, PM)

  // 2019-12-12 12:37:45
  System.out.printf("\n%tY-%tm-%td %tH:%tM:%tS", today, today, today, today, today, today);
  System.out.printf("\n%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", today);

  String str;
  str = String.format("\n%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", today);
  System.out.println(str);
  }
}