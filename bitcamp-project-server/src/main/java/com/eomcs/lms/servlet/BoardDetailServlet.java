package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.BoardObjectFileDao;
import com.eomcs.lms.domain.Board;

public class BoardDetailServlet implements Servlet {
  // List<Board> boards;
  BoardObjectFileDao boardDao;

  public BoardDetailServlet(/*List<Board> boards*/BoardObjectFileDao boardDao) {
    // this.boards = boards;
    this.boardDao = boardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    // 입력받은 번호와 일치하는 board 객체의 존재 여부를 list에서 검사하고
    // 있으면 그 board 객체를 리턴하는 코드 BoardObjectFileDao.findByNo(int)로 이동
    /*
    Board board = null;
    for (Board b : boards) {
      if (b.getNo() == no) {
        board = b;
        break;
      }
    }

    if (board != null) {
      out.writeUTF("OK");
      out.writeObject(board);

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
     */

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