package com.eomcs.basic.ex07.assignment;
//과제: 재귀호출을 이용하여 n!을 계산하라.
//실행)
//입력? 5
//5! = 1 * 2 * 3 * 4 * 5 = 120

import java.util.Scanner;

public class Test04 {
  public static void main(String[] args) {
    // 사용자로부터 정수 값을 입력 받는다.
    Scanner sc = new Scanner(System.in);
    System.out.print("입력? ");
    int n = sc.nextInt();

    // n! 을 계산한다.
    int result = factorial(n);

    // 결과 값을 출력한다.
    System.out.printf("%d! = ", n);   
    for (int i = 1; i <= n; i++) {
      System.out.print(i + " * ");
    }
    System.out.print("= " + result);
  }
  static int factorial(int n) {
    // 코드를 완성하시오!
    if (n <= 1)
      return n;
    else
      return factorial(n-1) * n;

    //   int fac = 1;
    //   for (int i = 1; i <= n; i++) {
    //     fac *= i;
    //   }
    //   return fac;
  }
}
