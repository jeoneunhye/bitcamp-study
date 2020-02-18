package com.eomcs.lms.handler;
// "/member/detail" 명령어 처리
import java.util.List;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Prompt;

public class MemberDetailCommand implements Command {
  Prompt prompt;
  List<Member> memberList;

  public MemberDetailCommand(Prompt prompt, List<Member> list) {
    this.prompt = prompt;
    memberList = list;
  }

  @Override
  public void execute() {
    int index = indexOfMember(prompt.inputInt("번호? "));
    if (index == -1) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    Member member = this.memberList.get(index);
    System.out.printf("번호: %d\n", member.getNo());
    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("암호: %s\n", member.getPassword());
    System.out.printf("사진: %s\n", member.getPhoto());
    System.out.printf("전화: %s\n", member.getTel());
    System.out.printf("가입일: %s\n", member.getRegisteredDate());
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