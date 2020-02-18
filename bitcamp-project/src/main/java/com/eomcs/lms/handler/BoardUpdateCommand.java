package com.eomcs.lms.handler;
// "/board/update" 명령어 처리
import java.util.List;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardUpdateCommand implements Command {
  Prompt prompt;
  List<Board> boardList;

  public BoardUpdateCommand(Prompt prompt, List<Board> list) {
    this.prompt = prompt;
    this.boardList = list;
  }

  @Override
  public void execute() {
    int index = indexOfBoard(prompt.inputInt("번호? "));
    if (index == -1) {
      System.out.println("해당 번호의 게시물이 없습니다.");
      return;
    }

    Board oldBoard = this.boardList.get(index);
    Board newBoard = new Board();

    newBoard.setNo(oldBoard.getNo());
    newBoard.setViewCount(oldBoard.getViewCount());
    newBoard.setTitle(prompt.inputString(String.format("내용(%s)? ", oldBoard.getTitle()),
        oldBoard.getTitle()));
    newBoard.setDate(oldBoard.getDate());
    newBoard.setWriter(oldBoard.getWriter());

    if (oldBoard.equals(newBoard)) {
      System.out.println("게시물 변경을 취소하였습니다.");
      return;
    }

    this.boardList.set(index, newBoard);
    System.out.println("게시물을 변경했습니다.");
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