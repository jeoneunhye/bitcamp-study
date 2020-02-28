package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.BoardObjectFileDao;

public class BoardDeleteServlet implements Servlet {
  // List<Board> boards;
  BoardObjectFileDao boardDao;

  public BoardDeleteServlet(/*List<Board> boards*/BoardObjectFileDao boardDao) {
    // this.boards = boards;
    this.boardDao = boardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    // 데이터 중복 검사, list에서 해당 번호의 Board 객체를 삭제하는 코드
    // BoardObjectFileDao.delete(int)로 이동
    /*
    int index = -1;
    for (int i = 0; i < boards.size(); i++) {
      if (boards.get(i).getNo() == no) {
        index = i;
        break;
      }
    }

    if (index == -1) {
      boards.remove(index);

      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
     */

    if (boardDao.delete(no) > 0) { // delete(int); 삭제했으면 1을 리턴
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }
}