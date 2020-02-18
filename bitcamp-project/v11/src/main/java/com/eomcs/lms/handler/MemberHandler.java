package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;

public class MemberHandler {
  static class Member {
    int no;
    String name;
    String email;
    String password;
    String photo;
    String tel;
    Date registeredDate;
  }
  static final int MEMBER_SIZE = 100;
  static int memberCount = 0;
  static Member[] members = new Member[MEMBER_SIZE];
  
  public static Scanner keyboard;
  // 호출만 했기 때문에 App 클래스가 쓰는 키보드를 가져와야 한다
  
  public static void addMember() {
    Member member = new Member();
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
    member.registeredDate = new Date(System.currentTimeMillis());  // 현재 날짜에 대한 밀리초를 날짜 형식으로 변경

    members[memberCount++] = member;
    System.out.println("저장하였습니다.");
  }

  public static void listMember() {
    for (int i = 0; i < memberCount; i++) {
      Member m = members[i];
      System.out.printf("%d, %s, %s, %s, %s\n",
          m.no, m.name, m.email, m.tel, m.registeredDate);
    }
  }
}
