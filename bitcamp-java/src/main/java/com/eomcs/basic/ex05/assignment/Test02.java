package com.eomcs.basic.ex05.assignment;

import java.util.Scanner;

// # 과제2 : 다섯 개의 정수 값을 입력받고 최소, 최대값을 구하라.
// 실행 예)
// 입력? 4 17 -1 6 9
// 최소값: -01
// 최대값: 17

public class Test02 {
  public static void main(String[] args) {
    Scanner keyScan = new Scanner(System.in);

    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    // 처음 입력한 값을 min, max에 집어 넣고 그 다음 입력받는 값과 비교하기 위해!!!!!

    System.out.print("입력? ");
    while (true) {
      int no = keyScan.nextInt();
      if (no == 0)  // 입력하고 싶은 갯수만큼 집어 넣고 0을 넣으면 멈춘다.
        break;  // 단점 : 입력값을 0으로 넣지 못한다.
      min = no < min ? no : min; // no가 min보다 작으면 no에 min을 넣어라
      max = no > max ? no : max; // no가 max보다 크면 no에 max를 넣어라
    }

    
    System.out.printf("최소값: %d\n", min);
    System.out.printf("최대값: %d\n", max);
    keyScan.close();
  }
}
