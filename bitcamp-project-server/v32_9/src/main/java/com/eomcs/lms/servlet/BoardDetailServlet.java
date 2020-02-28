package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.json.BoardJsonFileDao;
import com.eomcs.lms.domain.Board;

public class BoardDetailServlet implements Servlet {
  // json파일을 다루도록 객체를 변경 XxxObjectFileDao -> XxxJsonFileDao
  BoardJsonFileDao boardDao;

  public BoardDetailServlet(BoardJsonFileDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    Board board = boardDao.findByNo(no); // BoardObjectFileDao.findByNo(int);

    if (board != null) {
      out.writeUTF("OK");
      out.writeObject(board);
      // 해당 번호의 board 객체의 데이터를 클라이언트쪽에서 꺼내 조회하기 위해 출력 필요!

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }
}