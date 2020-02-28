package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.json.MemberJsonFileDao;
import com.eomcs.lms.domain.Member;

public class MemberDetailServlet implements Servlet {
  // json파일을 다루도록 객체를 변경 MemberObjectFileDao -> MemberJsonFileDao
  MemberJsonFileDao memberDao;

  public MemberDetailServlet(MemberJsonFileDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    Member member = memberDao.findByNo(no); // MemberObjectFileDao.findByNo(int);

    if (member != null) {
      out.writeUTF("OK");
      out.writeObject(member);
      // 해당 번호의 member 객체 데이터를 클라이언트쪽에서 꺼내 조회하기 위해 출력 필요!

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 회원이 없습니다.");
    }
  }
}