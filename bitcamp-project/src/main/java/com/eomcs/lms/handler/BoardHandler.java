package com.eomcs.lms.handler;
// listBoard() 메서드 변경
// => toArray()의 리턴 값을 사용하는 대신 iterator()의 리턴 값을 사용하여 목록 출력
import java.sql.Date;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Iterator;
import com.eomcs.util.List;
import com.eomcs.util.Prompt;

public class BoardHandler {
  List<Board> boardList;
  
  Prompt prompt;
  
  public BoardHandler(Prompt prompt, List<Board> list) {
    // List 파라미터는 List 인터페이스를 구현한 객체를 받는다.
    this.prompt = prompt;
    this.boardList = list;
  }

  public void addBoard() {
    Board board = new Board();

    board.setNo(prompt.inputInt("번호? "));
    board.setTitle(prompt.inputString("내용? "));
    board.setDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);

    boardList.add(board);
    
    System.out.println("저장하였습니다.");
  }

  public void listBoard() {
    // <1>
    // for (int i = 0; i < boardList.size(); i++)
    // Board b = boardList.get(i);
    
    // <2>
    // Board[] arr = new Board[this.boardList.size()];
    // for (Board b : arr) {

    // BoardList에게 값을 꺼내는 일을 해줄 Iterator 객체를 달라고 한다.
    Iterator<Board> iterator = boardList.iterator();
    // Iterator 객체에게 목록에서 꺼낼 값이 있는지 물어본다.
    while (iterator.hasNext()) {
      // 값이 있다고 한다면 그 값을 꺼내 달라고 요청한다.
      Board b = iterator.next();
      
      if (b == null)
        break;
      System.out.printf("%d, %s, %s, %d\n",
          b.getNo(), b.getTitle(), b.getDate(), b.getViewCount());
    } // 값이 없어질 때까지 반복!
  }

  public void detailBoard() {
    int index = indexOfBoard(prompt.inputInt("번호? "));
    
    if (index == -1) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    
    Board board = this.boardList.get(index);
    System.out.printf("번호: %d\n", board.getNo());
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("등록일: %s\n", board.getDate());
    System.out.printf("조회수: %d\n", board.getViewCount());
  }
  
  public void updateBoard() {
    int index = indexOfBoard(prompt.inputInt("번호? "));
    
    if (index == -1) {
      System.out.println("해당 번호의 게시글이 없습니다.");
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
    
    if (oldBoard.equals(newBoard)) {
      System.out.println("게시글 변경을 취소하였습니다.");
      return;
    }
    
    this.boardList.set(index, newBoard);
    System.out.println("게시글을 변경했습니다.");
  }
  
  public void deleteBoard() {
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