package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;
public class App2 {
  static int count = 0;  
  static String response;
  static final int SIZE = 100;
  // 일반 변수로 보이지 않기 위해 상수 'size'를 대문자로 작성하자
  static Member[] members = new Member[SIZE];
  
  static int[] no = new int[SIZE];
  static String[] name = new String[SIZE];
  static String[] email = new String[SIZE];
  static String[] password = new String[SIZE];
  static String[] photo = new String[SIZE];
  static String[] tel = new String[SIZE];
  static Date[] registeredDate = new Date[SIZE];

  static public class Member {  //static 붙여줌
    int no;
    String name;
    String email;
    String password;
    String photo;
    String tel;
    Date registeredDate;
  }
  public static void main(String[] args) {

    inputUsers();
    System.out.println();
    printUsers();

  }

  static void inputUsers() {
    
    Scanner keyboard = new Scanner(System.in);
//    for (int i = 0; i < SIZE; i++) {
//      members[i] = new Member();        // 인스턴스 생성 new 설계도명;
//    }
    for (int i = 0; i < SIZE; i++) {
      Member member = members[i];       // 간결화
      
      System.out.print("번호? ");
      member.no = keyboard.nextInt();
      keyboard.nextLine();
      System.out.print("이름? ");
      member.name = keyboard.nextLine();
      System.out.print("이메일? ");
      member.email = keyboard.nextLine();
      System.out.print("암호? ");
      member.password = keyboard.nextLine();
      System.out.print("사진? ");
      member.photo = keyboard.nextLine();
      System.out.print("전화? ");
      member.tel = keyboard.nextLine();
      //System.out.print("가입일? ");
      member.registeredDate = new Date(System.currentTimeMillis());  // 현재 날짜에 대한 밀리초를 날짜 형식으로 변경
      
      members[i] = member;
      // 회원 정보가 담겨있는 인스턴스의 주소(멤버 객체)를 레퍼런스 배열에 보관한다.

      count ++; // 입력받은 만큼만 출력하기 위해

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
      Member member = members[j];
      System.out.printf("%d, %s, %s, %s, %s\n",
          member.no, member.name, member.email, member.tel, member.registeredDate);
    }
  }
}