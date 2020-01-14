package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.ArrayList;
import com.eomcs.util.LinkedList;
import com.eomcs.util.Prompt;

public class MemberHandler {
  //public Scanner input;
  Prompt prompt;
  LinkedList<Member> memberList;

  public MemberHandler(Prompt prompt) {
//    this.input = input;
    this.prompt = prompt;
    memberList = new LinkedList<>();
  }

  public void addMember() {
    Member member = new Member();
    
    member.setNo(prompt.inputInt("번호? "));
    member.setName(prompt.inputString("이름? "));
    member.setEmail(prompt.inputString("이메일? "));
    member.setPassword(prompt.inputString("암호? "));
    member.setPhoto(prompt.inputString("사진? "));
    member.setTel(prompt.inputString("전화? "));
    member.setRegisteredDate(prompt.inputDate("가입일? "));
    
//    System.out.print("번호? ");
//    member.setNo(prompt.input.nextInt());
//    input.nextLine();
//    System.out.print("이름? ");
//    member.setName(input.nextLine());
//    System.out.print("이메일? ");
//    member.setEmail(input.nextLine());
//    System.out.print("암호? ");
//    member.setPassword(input.nextLine());
//    System.out.print("사진? ");
//    member.setPhoto(input.nextLine());
//    System.out.print("전화? ");
//    member.setTel(input.nextLine());
//    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    memberList.add(member);
    System.out.println("저장하였습니다.");
  }

  public void listMember() {
    Member[] arr = this.memberList.toArray(new Member[] {});
    //Member[] arr = this.memberList.toArray(new Member[this.memberList.size()]);
    // Object[] arr = this.memberList.toArray(Member[].class);
    for (Member m/*Object obj*/ : arr) {
      // Member m = (Member)obj;
      System.out.printf("%d, %s, %s, %s, %s\n",
          m.getNo(), m.getName(), m.getEmail(), m.getTel(), m.getRegisteredDate());
    }
  }
  
  public void detailMember() {
//    System.out.println("번호? ");
//    int no = input.nextInt();
//    input.nextLine();
//
//    int index = indexOfMember(no);
    //Member member = this.memberList.get(index);
    
    int index = indexOfMember(prompt.inputInt("번호? "));

    if (index == -1/*member != null*/) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }
    
    Member member = this.memberList.get(index); // 추가
    
    System.out.printf("번호: %d\n", member.getNo());
    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("암호: %s\n", member.getPassword());
    System.out.printf("사진: %s\n", member.getPhoto());
    System.out.printf("전화: %s\n", member.getTel());
    System.out.printf("가입일: %s\n", member.getRegisteredDate());
  }
  
  public void updateMember() {
//    System.out.println("번호? ");
//    int index = input.nextInt();
//    input.nextLine();
//    Member member = this.memberList.get(index);
    
    int index = indexOfMember(prompt.inputInt("번호? "));
    
    if (index == -1) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    
    Member oldMember = this.memberList.get(index);
    Member newMember = new Member();
    
//    String inputStr;
    boolean changed = false;
    
    newMember.setName(prompt.inputString(String.format("이름(%s)? ", oldMember.getName()),
        oldMember.getName()));
    
//    System.out.printf("이름(%s)? ", oldMember.getName());
//    inputStr = input.nextLine();
//    if (inputStr.equals("")) {
//      newMember.setName(oldMember.getName());
//    } else {
//      newMember.setName(inputStr);
//      changed = true;
//    }
    
    newMember.setEmail(prompt.inputString(String.format("이메일(%s)? ", oldMember.getEmail()),
        oldMember.getEmail()));
    
//    System.out.printf("이메일(%s)? ", oldMember.getEmail());
//    inputStr = input.nextLine();
//    if (inputStr.equals("")) {
//      newMember.setEmail(oldMember.getEmail());
//    } else {
//      newMember.setEmail(inputStr);
//      changed = true;
//    }
    
    newMember.setPassword(prompt.inputString(String.format("암호(%s)? ", oldMember.getPassword()),
        oldMember.getPassword()));
    
//    System.out.printf("암호(%s)? ", oldMember.getPassword());
//    inputStr = input.nextLine();
//    if (inputStr.equals("")) {
//      newMember.setPassword(oldMember.getPassword());
//    } else {
//      newMember.setPassword(inputStr);
//      changed = true;
//    }
    
    newMember.setPhoto(prompt.inputString(String.format("사진(%s)? ", oldMember.getPhoto()),
        oldMember.getPhoto()));
    
//    System.out.printf("사진(%s)? ", oldMember.getPhoto());
//    inputStr = input.nextLine();
//    if (inputStr.equals("")) {
//      newMember.setPhoto(oldMember.getPhoto());
//    } else {
//      newMember.setPhoto(inputStr);
//      changed = true;
//    }
    
    newMember.setTel(prompt.inputString(String.format("전화(%s)? ", oldMember.getTel()),
        oldMember.getTel()));
    
//    System.out.printf("전화(%s)? ", oldMember.getTel());
//    inputStr = input.nextLine();
//    if (inputStr.equals("")) {
//      newMember.setTel(oldMember.getTel());
//    } else {
//      newMember.setTel(inputStr);
//      changed = true;
//    }
    
    if (oldMember.equals(newMember)) {
      System.out.println("회원 변경을 취소하였습니다.");
      return;
    }
    
    this.memberList.set(index, newMember);
    System.out.println("회원을 변경했습니다.");
    
//    if (changed) {
//    this.memberList.set(index, newMember);
//    System.out.println("회원을 변경했습니다.");
//    } else
//      System.out.println("회원 변경을 취소했습니다.");
  }
  
  public void deleteMember() {
    int index = indexOfMember(prompt.inputInt("번호? "));
    
    //Member member = this.memberList.get(index);

    if (index == -1/*member == null*/) {
      System.out.println("해당 회원을 찾을 수 없습니다.");
      return;
    }
    
    this.memberList.remove(index);
    
    System.out.println("회원을 삭제했습니다.");
  }
  
  private int indexOfMember(int no) {
    for (int i = 0; i < this.memberList.size(); i++) {
      if (this.memberList.get(i).getNo() == no) {
        return i;
      }
  }
    return -1;
  }
}