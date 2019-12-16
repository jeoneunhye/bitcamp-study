package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;
public class App {
  static final int size = 100;
  static int[] no = new int[size];
  static String[] title = new String[size];
  static Date[] startDate = new Date[size];
  static Date[] endDate = new Date[size];
  static int[] totalHours = new int[size];
  static int count = 0;

  public static void main(String[] args) {
    inputBoards();
    System.out.println();
    printBoards();
  }
   static void inputBoards() { 
    Scanner keyboard = new Scanner(System.in);   
    String[] description = new String[size];
    int[] dayHours = new int[size];

    String response;
    
    for (int i = 0; i < size; i++) {
      System.out.print("번호? ");    
      no[i] = keyboard.nextInt();
      keyboard.nextLine();    
      System.out.print("수업명? ");
      title[i] = keyboard.nextLine();
      System.out.print("수업내용? ");
      description[i] = keyboard.nextLine();
      System.out.print("시작일? ");
      startDate[i] = Date.valueOf(keyboard.next());
      System.out.print("종료일? ");
      endDate[i] = Date.valueOf(keyboard.next());
      System.out.print("총수업시간? ");
      totalHours[i] = keyboard.nextInt();
      System.out.print("일수업시간? ");
      dayHours[i] = keyboard.nextInt();
      keyboard.nextLine();
      
      count++;
      
      System.out.println("계속 입력하시겠습니까?(Y/n)");
      response = keyboard.nextLine();
      if (!response.equalsIgnoreCase("y")) {
        break;
      }
    }
    keyboard.close();
   }

    static void printBoards() {
    for (int i = 0; i < count; i++) {
    System.out.printf("%d, %s, %s ~ %s, %d\n",
        no[i], title[i], startDate[i], endDate[i], totalHours[i]);
    }
  }
}
