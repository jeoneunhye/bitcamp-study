package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.BoardObjectFileDao;
import com.eomcs.lms.domain.Board;

public class BoardUpdateServlet implements Servlet {
  // List<Board> boards;
  BoardObjectFileDao boardDao;

  public BoardUpdateServlet(/*List<Board> boards*/BoardObjectFileDao boardDao) {
    // this.boards = boards;
    this.boardDao = boardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Board board = (Board) in.readObject();

    // 데이터 중복 검사, list의 index번 board 객체를 변경하는 코드
    // BoardObjectFileDao.update(Board)로 이동
    /*
    int index = -1;
    for (int i = 0; i < boards.size(); i++) {
      if (boards.get(i).getNo() == board.getNo()) {
        index = i;
        break;
      }
    }

    if (index != -1) {
      boards.set(index, board);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
     */

    if (boardDao.update(board) > 0) {
      // update(Board); list의 index번 board 객체를 변경했으면 1을 리턴
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }
}