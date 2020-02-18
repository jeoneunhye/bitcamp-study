package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Member;

public class MemberHandler {

  //  int memberCount = 0;
  //  Member[] members;

  static final int MEMBER_SIZE = 100;
  public Scanner input;
  MemberList memberList;

  public MemberHandler(Scanner input) {
    this.input = input;
    // this.members = new Member[MEMBER_SIZE];
    memberList = new MemberList();
  }

  public MemberHandler(Scanner input, int capacity) {
    this.input = input;
    memberList = new MemberList(capacity);
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

    // this.members[this.memberCount++] = member;
    memberList.add(member);
    System.out.println("저장하였습니다.");
  }

  public void listMember() {
    // for (int i = 0; i < this.memberCount; i++) {
    // Member m = this.members[i];
    Member[] members = memberList.toArray();
    for (Member m : members) {
      System.out.printf("%d, %s, %s, %s, %s\n",
          m.getNo(), m.getName(), m.getEmail(), m.getTel(), m.getRegisteredDate());
    }
  }
  //  }
}