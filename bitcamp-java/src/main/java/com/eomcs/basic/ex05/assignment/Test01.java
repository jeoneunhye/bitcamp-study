package com.eomcs.basic.ex05.assignment;

import java.util.Scanner;   // 가져오는 것이 아니고 선언하는 것

// # 과제! : 입력받은 두 정수 사이의 합계를 구하라.
// 실행 예)
// 입력? 2 5
// 2에서 5까지의 합은 14입니다.

public class Test01 {
  public static void main(String[] args) {
    Scanner keyScan = new Scanner(System.in);   // System.in자리 : data의 inputstream을 위한 도구
    // reference->리모컨      변환,자름     필수도구 dependency object를 injection(D.I)
    // 쥬서기 my = new 쥬서기(칼날);
    // 컵 c = my.play(과일);

    System.out.print("입력? ");
    int start = keyScan.nextInt();
    int end = keyScan.nextInt();

    int sum = 0;  // 합을 저장할 메모리 생성 + 초기화 필요
    for (int no = start; no <= end; no++) {    // start가 end가 될 때까지 no 하나씩 증가
      sum += no;    // sum = sum + no;
    }
    System.out.printf("%d에서 %d까지의 합은 %d입니다.\n", start, end , sum);
    //입력? 3 6
    //7에서 6까지의 합은 18입니다.   no1의 값이 변해버림
    //결과 피드백을 바탕으로 코드 수정 => 초기 입력 값이 변경되는 문제가 있음
    //no라는 또다른 변수를 생성하여 start값을 넣어줌
    keyScan.close();
  }
}