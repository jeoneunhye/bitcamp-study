package com.eomcs.lms.handler;

import java.sql.Date;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Iterator;
import com.eomcs.util.List;
import com.eomcs.util.Prompt;

public class BoardHandler {
  Prompt prompt;
  List<Board> boardList;

  public BoardHandler(Prompt prompt, List<Board> list) {
    this.prompt = prompt;
    this.boardList = list;
  }

  public void addBoard() {
    Board board = new Board();
    board.setNo(prompt.inputInt("번호? "));
    board.setTitle(prompt.inputString("내용? "));
    board.setDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);
    board.setWriter(prompt.inputString("작성자? "));

    boardList.add(board);
    System.out.println("저장하였습니다.");
  }

  public void listBoard() {
    Iterator<Board> iterator = boardList.iterator();
    while (iterator.hasNext()) {
      Board b = iterator.next();

      if (b == null)
        break;
      System.out.printf("%d, %s, %s, %d, %s\n",
          b.getNo(), b.getTitle(), b.getDate(), b.getViewCount(), b.getWriter());
    }
  }

  public void detailBoard() {
    int index = indexOfBoard(prompt.inputInt("번호? "));
    if (index == -1) {
      System.out.println("해당 번호의 게시물이 없습니다.");
      return;
    }

    Board board = this.boardList.get(index);
    System.out.printf("번호: %d\n", board.getNo());
    System.out.printf("내용: %s\n", board.getTitle());
    System.out.printf("등록일: %s\n", board.getDate());
    System.out.printf("조회수: %d\n", board.getViewCount());
    System.out.printf("작성자: %s\n", board.getWriter());
  }

  public void updateBoard() {
    int index = indexOfBoard(prompt.inputInt("번호? "));
    if (index == -1) {
      System.out.println("해당 번호의 게시물이 없습니다.");
      return;
    }

    Board oldBoard = this.boardList.get(index);
    Board newBoard = new Board();

    newBoard.setNo(prompt.inputInt(String.format("번호(%d)? ", oldBoard.getNo()),
        oldBoard.getNo()));

    newBoard.setViewCount(prompt.inputInt(String.format("조회수(%d)? ", oldBoard.getViewCount()),
        oldBoard.getViewCount()));

    newBoard.setTitle(prompt.inputString(String.format("내용(%s)? ", oldBoard.getTitle()),
        oldBoard.getTitle()));

    newBoard.setDate(prompt.inputDate(String.format("등록일(%s)? ", oldBoard.getDate()),
        oldBoard.getDate()));
    
    newBoard.setWriter(prompt.inputString(String.format("작성자(%s)? ", oldBoard.getWriter()),
        oldBoard.getWriter()));

    if (oldBoard.equals(newBoard)) {
      System.out.println("게시물 변경을 취소하였습니다.");
      return;
    }

    this.boardList.set(index, newBoard);
    System.out.println("게시물을 변경했습니다.");
  }

  public void deleteBoard() {
    int index = indexOfBoard(prompt.inputInt("번호? "));
    if (index == -1) {
      System.out.println("해당 번호의 게시물이 없습니다.");
      return;
    }

    this.boardList.remove(index);
    System.out.println("게시물을 삭제했습니다.");
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