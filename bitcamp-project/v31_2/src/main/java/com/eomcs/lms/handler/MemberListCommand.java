package com.eomcs.lms.handler;
// "/member/list" 명령어 처리
import java.util.Iterator;
import java.util.List;
import com.eomcs.lms.domain.Member;

public class MemberListCommand implements Command {
  List<Member> memberList;

  public MemberListCommand(List<Member> list) {
    memberList = list;
  }

  @Override
  public void execute() {
    Iterator<Member> iterator = memberList.iterator();
    while (iterator.hasNext()) {
      Member m = iterator.next();

      System.out.printf("%d, %s, %s, %s, %s\n",
          m.getNo(), m.getName(), m.getEmail(), m.getTel(), m.getRegisteredDate());
    }
  }
}