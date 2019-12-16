package com.bitcamp.myproject;

import java.sql.Date;
import java.util.Scanner;

public class App2 {
  static final int size = 100;
  static int[] no = new int[size];
  static String[] name = new String[size];
  static String[] email = new String[size];
  static String[] password = new String[size];
  static String[] grade = new String[size];
  static int[] textCount = new int[size];
  static int[] commentCount = new int[size];
  static int[] visitDate = new int[size];
  static Date[] registeredDate = new Date[size];
  static int count = 0;

  public static void main(String[] args) {
    inputUsers();
    System.out.println();
    printUsers();
  }

  static void inputUsers() {
    Scanner keyboard = new Scanner(System.in);
    String response;
    for (int i = 0; i < size; i++) {
      System.out.print("번호? ");
      no[i] = keyboard.nextInt();
      keyboard.nextLine();
      System.out.print("이름? ");
      name[i] = keyboard.nextLine();
      System.out.print("이메일주소? ");
      email[i] = keyboard.nextLine();
      System.out.print("암호? ");
      password[i] = keyboard.nextLine();
      System.out.print("등급? ");
      grade[i] = keyboard.nextLine();
      System.out.print("작성글 수? ");
      textCount[i] = keyboard.nextInt();
      System.out.print("작성댓글 수? ");
      commentCount[i] = keyboard.nextInt();
      keyboard.nextLine();
      System.out.print("방문일? ");
      visitDate[i] = keyboard.nextInt();
      keyboard.nextLine();
      System.out.print("가입일? ");
      registeredDate[i] = Date.valueOf(keyboard.nextLine());

      count++;

      System.out.println("계속 입력하시겠습니까?(Y/n)");
      response = keyboard.nextLine();
      if (!response.equalsIgnoreCase("y")) {
        break;
      }
    }
    keyboard.close();
  }

  static void printUsers() {
    for (int i = 0; i < count; i++) {
      System.out.printf("%d, %s, %s, %s, %s, 글 %d개, 댓글 %d개, %d, %s",
          no[i], name[i], email[i], password[i], grade[i],
          textCount[i], commentCount[i], visitDate[i], registeredDate[i]);
    }
  }
}
