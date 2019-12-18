package com.eomcs.basic.ex06.assignment;

import java.util.Scanner;

public class Console {
  
//  static int inputInt() {                     // 밑의 String값을 입력받는 메서드와 동시에 사용 가능함
//    Scanner keyScan = new Scanner(System.in);
//    System.out.print("밑변 길이? ");
//    int width = keyScan.nextInt();
//    keyScan.close();
//    return width;
//  }
  @Deprecated   // (비난받는) 애노테이션. 다른 클래스에서 Console(); 입력하면 사용하지 못하게 함
  static int inputInt() {
    return inputInt("밑변 길이? "); // console의 inputInt를 호출 -> test01 
  }
  
  static int inputInt(String message) {
    Scanner keyScan = new Scanner(System.in);
//    System.out.print("밑변 길이? ");
      System.out.print(message);
    int width = keyScan.nextInt();
//    keyScan.close();
    return width;
  }
}
