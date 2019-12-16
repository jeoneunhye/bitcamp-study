package com.eomcs.basic.ex05.assignment;

import java.util.Scanner;

public class Test01 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("숫자를 입력하세요! ");
    int no1 = sc.nextInt();
    int no2 = sc.nextInt();

    int sum1 = 0, sum2 = 0;
    for (int i = 0; i <= no2; i++) {
      sum2 = sum2 + i; }
    for (int i = 0; i <= no1 - 1; i++) {
      sum1 = sum1 + i; }
    System.out.printf("%d에서 %d까지의 합은 %d 입니다. ", no1, no2, Math.abs(sum2 - sum1));
    
  }
}
