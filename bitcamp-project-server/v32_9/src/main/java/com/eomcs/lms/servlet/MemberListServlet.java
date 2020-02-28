package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.json.MemberJsonFileDao;

public class MemberListServlet implements Servlet {
  // json파일을 다루도록 객체를 변경 MemberObjectFileDao -> MemberJsonFileDao
  MemberJsonFileDao memberDao;

  public MemberListServlet(MemberJsonFileDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(memberDao.findAll()); // findAll(); List<Member>를 리턴
    // List의 데이터를 클라이언트쪽에서 꺼내 조회하기 위해 출력 필요!
  }
}