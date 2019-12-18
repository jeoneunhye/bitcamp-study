package com.eomcs.basic.ex06.assignment;

//import java.util.Scanner;

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

public class Test01 {
  public static void main(String[] args) {
    int width = Console.inputInt();
    int line = 0;
    while (line++ < width) {
      Graphic.drawLine(line);                 // Graphic 안의 drawline을 실행해라
      System.out.println(); 
    }

  }
//  static int inputInt() {        // 사용자의 입력값 int를 받는 클래스
//    Scanner keyScan = new Scanner(System.in);
//    System.out.print("밑변 길이? ");
//    int width = keyScan.nextInt();
//    keyScan.close();
//    return width;
//  }

//  static void drawLine(int length) {
//    int x = 0;
//    while (x++ < length) {                => Graphic 클래스로 이동!
//      System.out.print("*");
//    }
//  }

}

