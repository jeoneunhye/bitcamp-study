package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.json.MemberJsonFileDao;

public class MemberDeleteServlet implements Servlet {
  // json파일을 다루도록 객체를 변경 MemberObjectFileDao -> MemberJsonFileDao
  MemberJsonFileDao memberDao;

  public MemberDeleteServlet(MemberJsonFileDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    if (memberDao.delete(no) > 0) { // delete(int); 삭제했으면 1을 리턴
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 회원이 없습니다.");
    }
  }
}