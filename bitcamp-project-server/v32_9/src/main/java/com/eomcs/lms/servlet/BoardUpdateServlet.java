package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.json.BoardJsonFileDao;
import com.eomcs.lms.domain.Board;

public class BoardUpdateServlet implements Servlet {
  // json파일을 다루도록 객체를 변경 BoardObjectFileDao -> BoardJsonFileDao
  BoardJsonFileDao boardDao;

  public BoardUpdateServlet(BoardJsonFileDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Board board = (Board) in.readObject();

    if (boardDao.update(board) > 0) {
      // update(Board); list의 index번 board 객체를 변경했으면 1을 리턴
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }
}