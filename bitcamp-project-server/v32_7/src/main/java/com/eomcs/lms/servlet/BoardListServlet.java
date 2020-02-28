package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.BoardObjectFileDao;

public class BoardListServlet implements Servlet {
  // List<Board> boards;
  BoardObjectFileDao boardDao;

  public BoardListServlet(/*List<Board> boards*/BoardObjectFileDao boardDao) {
    // this.boards = boards;
    this.boardDao = boardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    // List<Board>를 리턴하는 코드 BoardObjectFileDao.findAll()로 이동
    /*
    out.writeUTF("OK");
    out.reset();
    out.writeObject(boards);
     */

    out.writeUTF("OK");
    out.reset();
    out.writeObject(boardDao.findAll()); // findAll(); List<Board>를 리턴
    // List의 데이터를 클라이언트쪽에서 꺼내 조회하기 위해 출력 필요!
  }
}