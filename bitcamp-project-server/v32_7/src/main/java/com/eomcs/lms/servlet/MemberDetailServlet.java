package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.MemberObjectFileDao;
import com.eomcs.lms.domain.Member;

public class MemberDetailServlet implements Servlet {
  // List<Member> members;
  MemberObjectFileDao memberDao;

  public MemberDetailServlet(/*List<Member> members*/MemberObjectFileDao memberDao) {
    // this.members = members;
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    // 입력받은 번호와 일치하는 member 객체의 존재 여부를 list에서 검사하고
    // 있으면 그 member 객체를 리턴하는 코드 MemberObjectFileDao.findByNo(int)로 이동
    /*
    Member member = null;
    for (Member m : members) {
      if (m.getNo() == no) {
        member = m;
        break;
      }
    }

    if (member != null) {
      out.writeUTF("OK");
      out.writeObject(member);

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 회원이 없습니다.");
    }
     */

    Member member = memberDao.findByNo(no);// MemberObjectFileDao.findByNo(int);

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