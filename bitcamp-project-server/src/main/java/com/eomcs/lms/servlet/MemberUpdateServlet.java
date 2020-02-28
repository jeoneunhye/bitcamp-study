package com.eomcs.lms.servlet;
// 의존 객체를 MemberJsonFileDao 클래스 -> MemberDao 인터페이스로 변경
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberUpdateServlet implements Servlet {
  // 레퍼런스 타입으로 DAO 클래스를 구체적으로 지정하기보다는
  // 인터페이스를 지정함으로써
  // 향후 다른 구현체로 교체하기 쉽도록 한다.
  MemberDao memberDao;

  public MemberUpdateServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Member member = (Member) in.readObject();

    if (memberDao.update(member) > 0) {
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 회원이 없습니다.");
    }
  }
}