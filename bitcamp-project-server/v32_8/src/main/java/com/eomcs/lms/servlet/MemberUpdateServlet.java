package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.MemberObjectFileDao;
import com.eomcs.lms.domain.Member;

public class MemberUpdateServlet implements Servlet {
  MemberObjectFileDao memberDao;

  public MemberUpdateServlet(MemberObjectFileDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Member member = (Member) in.readObject();

    if (memberDao.update(member) > 0) {
      // update(Member); list의 index번 member 객체를 변경했으면 1을 리턴
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 회원이 없습니다.");
    }
  }
}