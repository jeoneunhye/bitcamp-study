package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.MemberObjectFileDao;
import com.eomcs.lms.domain.Member;

public class MemberAddServlet implements Servlet {
  // List<Member> members;
  MemberObjectFileDao memberDao;

  public MemberAddServlet(/*List<Member> members*/MemberObjectFileDao memberDao) {
    // this.members = members;
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Member member = (Member) in.readObject();

    // 데이터 중복 검사, 입력받은 member 객체를 list에 추가하는 코드
    // MemberObjectFileDao.insert(Member)로 이동
    /*
    int i = 0;
    for (; i < members.size(); i++) {
      if (members.get(i).getNo() == member.getNo()) {
        break;
      }
    }

    if (i == members.size()) {
      members.add(member);
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 회원이 있습니다.");
    }
     */

    if (memberDao.insert(member) > 0) { // insert(Member); 저장했으면 1을 리턴
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 회원이 있습니다.");
    }
  }
}