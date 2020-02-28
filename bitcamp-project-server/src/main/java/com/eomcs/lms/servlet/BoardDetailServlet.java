package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.eomcs.lms.domain.Board;

public class BoardDetailServlet implements Servlet {
  List<Board> boards;

  // service()가 사용할 의존 객체를 생성자로부터 받아 온다.
  // serverApp.processRequest()에서 servletMap.get("/board/detail");이 호출될 때
  // 이 클래스의 객체가 servlet 레퍼런스에 담긴다.
  public BoardDetailServlet(List<Board> boards) {
    this.boards = boards;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    // ServerApp에서 service()를 호출하면 예외를 받고, 그 예외를
    // 처리하는 try문을 썼기 때문에 이 메서드에서 예외를 처리할 필요없다.
    // try {
    int no = in.readInt();

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

    // } catch (Exception e) {
    // out.writeUTF("FAIL");
    // out.writeUTF(e.getMessage());
    // }
  }
}