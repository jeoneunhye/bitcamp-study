package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.MemberObjectFileDao;

public class MemberDeleteServlet implements Servlet {
  // List<Member> members;
  MemberObjectFileDao memberDao;

  public MemberDeleteServlet(/*List<Member> members*/MemberObjectFileDao memberDao) {
    // this.members = members;
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    // 데이터 중복 검사, list에서 해당 번호의 Member 객체를 삭제하는 코드
    // MemberObjectFileDao.delete(int)로 이동
    /*
    int index = -1;
    for (int i = 0; i < members.size(); i++) {
      if (members.get(i).getNo() == no) {
        index = i;
        break;
      }
    }

    if (index != -1) {
      members.remove(index);
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 회원이 없습니다.");
    }
     */

    if (memberDao.delete(no) > 0) { // delete(int); 삭제했으면 1을 리턴
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 회원이 없습니다.");
    }
  }
}