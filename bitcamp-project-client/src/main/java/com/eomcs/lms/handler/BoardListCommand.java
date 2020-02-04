package com.eomcs.lms.handler;
// "/board/list" 명령어 처리
import java.util.Iterator;
import java.util.List;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardListCommand implements Command {
  List<Board> boardList;

  Prompt prompt;

  public BoardListCommand(List<Board> list) {
    // App 클래스에서 넘겨준 List 인터페이스를 구현한 객체를 받는다.
    this.boardList = list;
  }

  @Override
  public void execute() {
    Iterator<Board> iterator = boardList.iterator();
    while (iterator.hasNext()) {
      Board b = iterator.next();

      if (b == null)
        break;
      System.out.printf("%d, %s, %s, %d, %s\n",
          b.getNo(), b.getTitle(), b.getDate(), b.getViewCount(), b.getWriter());
    }
  }
}