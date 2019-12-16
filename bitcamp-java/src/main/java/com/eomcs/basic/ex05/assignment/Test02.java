package com.eomcs.basic.ex05.assignment;

import java.util.Scanner;

public class Test02 {
public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  System.out.println("숫자를 입력하세요! ");
  int[] num = new int[5];
  num[0] = sc.nextInt();
  num[1] = sc.nextInt();
  num[2] = sc.nextInt();
  num[3] = sc.nextInt();
  num[4] = sc.nextInt();
  sc.nextLine();
  int min = num[0];
  for (int i = 0; i < num.length; i++) {
    if (min > num[i]) {
      min = num[i];
    }
     }
  int max = num[0];  
  for (int i = 0; i < num.length; i++) {
    if (max < num[i]) {
      max = num[i];
    }
     }
  System.out.printf("최소값: %d\n최대값: %d", min, max);
}
}
