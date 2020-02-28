package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.MemberObjectFileDao;

public class MemberListServlet implements Servlet {
  // List<Member> members;
  MemberObjectFileDao memberDao;

  public MemberListServlet(/*List<Member> members*/MemberObjectFileDao memberDao) {
    // this.members = members;
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    // List<Member>를 리턴하는 코드 MemberObjectFileDao.findAll()로 이동
    /*
    out.writeUTF("OK");
    out.reset();
    out.writeObject(members);
     */

    out.writeUTF("OK");
    out.reset();
    out.writeObject(memberDao.findAll()); // findAll(); List<Member>를 리턴
    // List의 데이터를 클라이언트쪽에서 꺼내 조회하기 위해 출력 필요!
  }
}