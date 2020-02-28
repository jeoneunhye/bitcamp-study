package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.BoardObjectFileDao;
import com.eomcs.lms.domain.Board;

public class BoardAddServlet implements Servlet {
  // List<Board> boards;
  BoardObjectFileDao boardDao;

  public BoardAddServlet(/*List<Board> boards*/BoardObjectFileDao boardDao) {
    // this.boards = boards;
    this.boardDao = boardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Board board = (Board) in.readObject();

    // 데이터 중복 검사, 입력받은 board 객체를 list에 추가하는 코드
    // BoardObjectFileDao.insert(Board)로 이동
    /*
    int i = 0;
    for (; i < boards.size(); i++) {
      if (boards.get(i).getNo() == board.getNo()) {
        break;
      }
    }

    if (i == boards.size()) {
      boards.add(board);
      System.out.println("게시물을 저장하였습니다.");

      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 게시물이 있습니다.");
    }
     */

    if (boardDao.insert(board) > 0) { // insert(Board); 저장했으면 1을 리턴
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 게시물이 있습니다.");
    }
  }
}