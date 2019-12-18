package com.eomcs.basic.ex06.assignment;

import java.util.Scanner;

// 사용자로부터 마름모의 가로 길이를 숫자를 입력 받아 '*' 문자로 그려라. 단 마름모의 절반만 그린다.
// 반복문을 사용할 때는 while 또는 do ~ while 문만을 사용하라!
// 실행 결과
/*
```
밑변 길이? 5
 *
 **
 ***
 ****
 *****
 ****
 ***
 **
 *
```
 */
public class Test021 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int i = 0;
    int j = 0;
    System.out.print("마름모의 가로 길이? ");
    int length = sc.nextInt();

    while (i < length) {
      i++;
      while (j < i) {
        System.out.print("*");
        j++;
      } 
      System.out.println();
      j = 0;
    } 
    i = 0;
    while (i < length - 1) {
      while (j < length - i - 1) {
        System.out.print("*");
        j++;
      }
      j = 0;
      System.out.println();
      i++;
    }


    sc.close();
  }
}
