package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;
import com.eomcs.util.AbstractList;
import com.eomcs.util.Prompt;

public class MemberHandler {
  // ArrayList나 LinkedList를 마음대로 사용할 수 있도록
  // 수업 목록을 관리하는 필드를 선언할 때
  // 이들 클래스의 수퍼 클래스로 선언한다.
  // 대신 이 필드에 들어갈 객체는 생성자에서 파라미터로 받는다.
  // 이렇게 하면 ArrayList도 사용할 수 있고, LinkedList도 사용할 수 있어
  // 유지보수에 좋다. 즉 선택의 폭이 넓어진다.
  /*Linked*/AbstractList<Member> memberList;
  
  Prompt prompt;

  public MemberHandler(Prompt prompt, AbstractList<Member> list) {
    this.prompt = prompt;
    
    //memberList = new LinkedList<>();
    memberList = list;
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

    memberList.add(member);
    System.out.println("저장하였습니다.");
  }

  public void listMember() {
    Member[] arr = this.memberList.toArray(new Member[] {});
    
    for (Member m : arr) {
      System.out.printf("%d, %s, %s, %s, %s\n",
          m.getNo(), m.getName(), m.getEmail(), m.getTel(), m.getRegisteredDate());
    }
  }
  
  public void detailMember() {
    int index = indexOfMember(prompt.inputInt("번호? "));

    if (index == -1) {
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
    int index = indexOfMember(prompt.inputInt("번호? "));
    
    if (index == -1) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    
    Member oldMember = this.memberList.get(index);
    Member newMember = new Member();
    
//    String inputStr;
   // boolean changed = false;
    
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
    
    newMember.setPassword(prompt.inputString(String.format("암호(%s)? ", oldMember.getPassword()),
        oldMember.getPassword()));
    
    newMember.setPhoto(prompt.inputString(String.format("사진(%s)? ", oldMember.getPhoto()),
        oldMember.getPhoto()));
    
    newMember.setTel(prompt.inputString(String.format("전화(%s)? ", oldMember.getTel()),
        oldMember.getTel()));
    
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
    
    if (index == -1) {
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