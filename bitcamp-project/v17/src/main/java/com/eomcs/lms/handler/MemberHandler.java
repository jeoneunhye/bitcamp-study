package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Member;

public class MemberHandler {
  static final int MEMBER_SIZE = 100;
  public Scanner input;
  ArrayList/*MemberList*/ memberList;

  public MemberHandler(Scanner input) {
    this.input = input;
    memberList = new ArrayList/*MemberList*/();
  }

  public MemberHandler(Scanner input, int capacity) {
    this.input = input;
    memberList = new ArrayList/*MemberList*/(capacity);
  }

  public void addMember() {
    Member member = new Member();
    System.out.print("번호? ");
    member.setNo(input.nextInt());
    input.nextLine();
    System.out.print("이름? ");
    member.setName(input.nextLine());
    System.out.print("이메일? ");
    member.setEmail(input.nextLine());
    System.out.print("암호? ");
    member.setPassword(input.nextLine());
    System.out.print("사진? ");
    member.setPhoto(input.nextLine());
    System.out.print("전화? ");
    member.setTel(input.nextLine());
    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    memberList.add(member);
    System.out.println("저장하였습니다.");
  }

  public void listMember() {
    Object[] arr/*Member[] members*/ = this.memberList.toArray();
    for (Object obj : arr/*Member m : members*/) {
      Member m = (Member)obj;   // 형변환 추가!
      System.out.printf("%d, %s, %s, %s, %s\n",
          m.getNo(), m.getName(), m.getEmail(), m.getTel(), m.getRegisteredDate());
    }
  }
}