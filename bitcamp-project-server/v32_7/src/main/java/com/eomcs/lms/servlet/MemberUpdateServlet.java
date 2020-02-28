package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.MemberObjectFileDao;
import com.eomcs.lms.domain.Member;

public class MemberUpdateServlet implements Servlet {
  // List<Member> members;
  MemberObjectFileDao memberDao;

  public MemberUpdateServlet(/*List<Member> members*/MemberObjectFileDao memberDao) {
    // this.members = members;
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Member member = (Member) in.readObject();

    // 데이터 중복 검사, list의 index번 member 객체를 변경하는 코드
    // MemberObjectFileDao.update(Member)로 이동
    /*
    int index = -1;
    for (int i = 0; i < members.size(); i++) {
      if (members.get(i).getNo() == member.getNo()) {
        index = i;
        break;
      }
    }

    if (index != -1) {
      members.set(index, member);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 회원이 없습니다.");
    }
     */

    if (memberDao.update(member) > 0) {
      // update(Member); list의 index번 member 객체를 변경했으면 1을 리턴
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 회원이 없습니다.");
    }
  }
}