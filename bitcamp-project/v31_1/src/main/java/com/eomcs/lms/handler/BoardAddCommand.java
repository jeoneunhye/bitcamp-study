package com.eomcs.lms.handler;
// "/board/add" 명령어 처리
import java.sql.Date;
import java.util.List;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardAddCommand implements Command {
  Prompt prompt;
  List<Board> boardList;

  public BoardAddCommand(Prompt prompt, List<Board> list) {
    this.prompt = prompt;
    this.boardList = list;
  }

  @Override
  public void execute() {
    Board board = new Board();
    board.setNo(prompt.inputInt("번호? "));
    board.setTitle(prompt.inputString("내용? "));
    board.setDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);
    board.setWriter(prompt.inputString("작성자? "));

    boardList.add(board);
    System.out.println("저장하였습니다.");
  }
}