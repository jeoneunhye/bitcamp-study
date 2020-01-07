package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.ArrayList;

public class MemberHandler {
  static final int MEMBER_SIZE = 100;
  public Scanner input;
  ArrayList<Member> memberList;

  public MemberHandler(Scanner input) {
    this.input = input;
    memberList = new ArrayList<>();
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
    // Member 객체의 목록을 저장할 배열을 넘기는데 크기가 0인 배열을 넘긴다.
    // toArray()는 내부에서 새 배열을 만들고
    Member[] arr = this.memberList.toArray(new Member[this.memberList.size()]);
    // Object[] arr = this.memberList.toArray(Member[].class);
    for (Member m/*Object obj*/ : arr) {
      // Member m = (Member)obj;
      System.out.printf("%d, %s, %s, %s, %s\n",
          m.getNo(), m.getName(), m.getEmail(), m.getTel(), m.getRegisteredDate());
    }
  }
  
  public void detailMember() {
    System.out.println("회원 인덱스? ");
    int index = input.nextInt();
    input.nextLine();

    Member member = this.memberList.get(index);

    if (member == null) {
      System.out.println("해당 회원을 찾을 수 없습니다.");
      return;
    }
    System.out.printf("번호: %d\n", member.getNo());
    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("암호: %s\n", member.getPassword());
    System.out.printf("사진: %s\n", member.getPhoto());
    System.out.printf("전화: %s\n", member.getTel());
    System.out.printf("가입일: %s\n", member.getRegisteredDate());
  }
  
  public void updateMember() {
    System.out.println("회원 인덱스? ");
    int index = input.nextInt();
    input.nextLine();

    Member oldMember = this.memberList.get(index);

    if (oldMember == null) {
      System.out.println("해당 회원을 찾을 수 없습니다.");
      return; // 메서드를 실행하지 않고 나감
    }

    System.out.printf("이름(%s)? ", oldMember.getName());
    String name = input.nextLine();
    System.out.printf("이메일(%s)? ", oldMember.getEmail());
    String email = input.nextLine();
    System.out.printf("암호(%s)? ", oldMember.getPassword());
    String pwd = input.nextLine();
    System.out.printf("사진(%s)? ", oldMember.getPhoto());
    String photo = input.nextLine();
    System.out.printf("전화(%s)? ", oldMember.getTel());
    String tel = input.nextLine();
    
    Member newMember = new Member();
    
    if (name.length() == 0)
      newMember.setName(oldMember.getName());
    newMember.setName(name);
    newMember.setEmail(email);
    newMember.setPassword(pwd);
    newMember.setPhoto(photo);
    newMember.setTel(tel);
    
    this.memberList.set(index, newMember);
    System.out.println("회원을 변경했습니다.");
  }
  
  public void deleteMember() {
    System.out.println("회원 인덱스? ");
    int index = input.nextInt();
    input.nextLine();

    Member member = this.memberList.get(index);

    if (member == null) {
      System.out.println("해당 회원을 찾을 수 없습니다.");
      return;
    }
    
    this.memberList.remove(index);
    
    System.out.println("회원을 삭제했습니다.");
  }
}