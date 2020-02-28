package com.eomcs.lms.handler;
// "/board/list" 명령어 처리
import java.util.Iterator;
import java.util.List;
import com.eomcs.lms.domain.Board;

public class BoardListCommand implements Command {
  List<Board> boardList;

  public BoardListCommand(List<Board> list) {
    this.boardList = list;
  }

  @Override
  public void execute() {
    Iterator<Board> iterator = boardList.iterator();
    while (iterator.hasNext()) {
      Board b = iterator.next();

      System.out.printf("%d, %s, %s, %d, %s\n",
          b.getNo(), b.getTitle(), b.getDate(), b.getViewCount(), b.getWriter());
    }
  }
}