package com.eomcs.basic.ex06.assignment;

import java.util.Scanner;

//사용자로부터 직삼각형의 밑변 숫자를 입력 받아 '*' 문자로 그려라. 
//반복문을 사용할 때는 while 또는 do ~ while 문만을 사용하라!
//실행 결과
/*
밑변 길이? 5
 *
 **
 ***
 ****
 *****
 */

public class test011 {
  public static void main(String[] args) {
    Scanner keyScan = new Scanner(System.in);
    int i = 0;
    int j = 0;
    System.out.print("밑변 길이? ");
    int length = keyScan.nextInt();
    while (i < length) {
      i++;
      while (j < i) {
        System.out.print("*");
        j++;
      } 
      System.out.println();
      j = 0;
    } 

    keyScan.close();
  }

}

