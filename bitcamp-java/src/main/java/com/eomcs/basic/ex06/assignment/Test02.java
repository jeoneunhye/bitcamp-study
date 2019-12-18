package com.eomcs.basic.ex06.assignment;

//import java.util.Scanner;

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
// 현재 과제와 유사한 결과를 내는 기존 소스를 가져온다.
public class Test02 {
  public static void main(String[] args) {
    int width = Console.inputInt(); // inputInt의 width와는 다른 width임!

    int line = 0;
    while (line++ < width) {
      Graphic.drawLine(line);
      System.out.println(); 
    }
    line--;

    while (--line > 0) {
      Graphic.drawLine(line);
      System.out.println();
    }
  }

//  static int inputInt() {        // 사용자의 입력값 int를 받는 클래스
//    Scanner keyScan = new Scanner(System.in);
//    System.out.print("밑변 길이? ");
//    int width = keyScan.nextInt();
//    keyScan.close();
//    return width;   // 메서드를 실행하면 width값이 놓임 -> main 블럭에 변수 선언이 필요 (int width = )
//  }

//  static void drawLine(int length) {
//    int x = 0;
//    while (x++ < length) {                => Graphic 클래스로 이동!
//      System.out.print("*");
//    }
//  }
}
