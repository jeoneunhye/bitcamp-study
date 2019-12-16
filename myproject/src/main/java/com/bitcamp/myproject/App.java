package com.bitcamp.myproject;

import java.sql.Date;
import java.util.Scanner;

public class App {
  static Scanner keyboard = new Scanner(System.in);
  static int size = 100;
  static int count = 0;
  static int[] no = new int[size];
  static String[] subject = new String[size];
  static String[] title = new String[size];
  static Date[] uploadDate = new Date[size];
  static String[] playTime = new String[size];

  public static void main(String[] args) {
    inputVideo();
    System.out.println();
    printVideo();
  }

  static void inputVideo() {
    String response;
    for (int i = 0; i < size; i++) {
      System.out.print("번호? ");
      no[i] = keyboard.nextInt();
      keyboard.nextLine();
      System.out.print("주제? ");
      subject[i] = keyboard.nextLine();
      System.out.print("제목? ");
      title[i] = keyboard.nextLine();
      System.out.print("업로드날짜? ");
      uploadDate[i] = Date.valueOf(keyboard.nextLine());
      System.out.print("재생시간? ");
      playTime[i] = keyboard.nextLine();

      count++;
      
      System.out.println("더 입력하시겠습니까?(Y/n)");
      response = keyboard.nextLine();
      if (!response.equalsIgnoreCase("y")) {
        break;
      }
    }
    keyboard.close();

  }

  static void printVideo() {
    for (int i = 0; i < count; i++) {
      System.out.printf("%d, %s, %s, %s, %s\n",
          no[i], subject[i], title[i], uploadDate[i], playTime[i]);
    }
  }
}
