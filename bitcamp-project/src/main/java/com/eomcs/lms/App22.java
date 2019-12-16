package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;
public class App22 {

  static int count = 0;  
  static String response;
  static final int size = 100;
  static User[] users = new User[size];

  public static void main(String[] args) {

    inputUsers();

    printUsers();

  }

  static void inputUsers() {
    
    Scanner keyboard = new Scanner(System.in);
    
    for (int i = 0; i < size; i++) {
      User u = new User();
      System.out.print("번호? ");
      u.no = keyboard.nextInt();
      keyboard.nextLine();
      System.out.print("이름? ");
      u.name = keyboard.nextLine();
      System.out.print("이메일? ");
      u.email = keyboard.nextLine();
      System.out.print("암호? ");
      u.password = keyboard.nextLine();
      System.out.print("사진? ");
      u.photo = keyboard.nextLine();
      System.out.print("전화? ");
      u.tel = keyboard.nextLine();
      System.out.print("가입일? ");
      u.registeredDate = Date.valueOf(keyboard.nextLine());
      users[i] = u;

      count ++;

      System.out.print("계속 입력하시겠습니까?(Y/n)");
      response = keyboard.nextLine();
      if (!response.equalsIgnoreCase("y")) {
        break;
      } 
    }
    keyboard.close();
  }

  static void printUsers() {
    for (int j = 0; j < count; j++) {
      User u = users[j];
      System.out.printf("%d, %s, %s, %s, %s\n",
          u.no, u.name, u.email, u.tel, u.registeredDate);
    }
  }
}