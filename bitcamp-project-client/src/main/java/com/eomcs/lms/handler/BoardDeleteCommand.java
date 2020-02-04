package com.eomcs.lms.handler;
// "/board/delete" 명령어 처리
import java.util.List;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardDeleteCommand implements Command {
  List<Board> boardList;

  Prompt prompt;

  public BoardDeleteCommand(Prompt prompt, List<Board> list) {
    // List 파라미터는 List 인터페이스를 구현한 객체를 받는다.
    this.prompt = prompt;
    this.boardList = list;
  }

  @Override
  public void execute() {
    int index = indexOfBoard(prompt.inputInt("번호? "));

    if (index == -1) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    this.boardList.remove(index);

    System.out.println("게시글을 삭제했습니다.");
  }

  private int indexOfBoard(int no) {
    for (int i = 0; i < this.boardList.size(); i++) {
      if (this.boardList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}