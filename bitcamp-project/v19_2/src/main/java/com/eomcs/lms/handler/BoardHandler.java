package com.eomcs.lms.handler;
// 게시글 번호로 객체를 찾는 코드를 관리하기 쉽게 별도의 메서드로 분리한다.
// => indexOfBoard(int) 메서드 추가
import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.ArrayList;

public class BoardHandler {
  ArrayList<Board> boardList;
  Scanner input;
  
  public BoardHandler(Scanner input) {
    this.input = input;
    this.boardList = new ArrayList<>();
  }

  public BoardHandler(Scanner input, int capacity) {
    this.input = input;
    this.boardList = new ArrayList<>(capacity);
  }
  
  public void addBoard() {
    Board board = new Board();

    System.out.print("번호? ");
    board.setNo(input.nextInt());
    input.nextLine();

    System.out.print("내용? ");
    board.setTitle(input.nextLine());

    board.setDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);

    boardList.add(board);
    // 파라미터로 E를 받기로 했기 때문에 반드시 board를 넣어야 한다!
    
    System.out.println("저장하였습니다.");
  }

  public void listBoard() {
    // ★BoardList의 보관된 값을 받을 배열을 준비한다.
    Board[] arr = new Board[this.boardList.size()]; // 전체가 아닌 count된 만큼만 배열 생성
    
    // toArray()에게 빈 배열을 넘겨서 복사받는다.
    Board[] arr2 = this.boardList.toArray(arr);
    
    //System.out.println(arr == arr2);    // true
    // 새로 배열을 만든 게 아니라 넘겨준 배열을 복사한 것이라는 걸 알 수 있다.
    
    for (Board b : arr) {
      if (b == null)
        break;  // 전체를 출력하지 않도록
      System.out.printf("%d, %s, %s, %d\n",
          b.getNo(), b.getTitle(), b.getDate(), b.getViewCount());
    }
  }

  public void detailBoard() {
    System.out.print("번호? ");
    int no/*index*/ = input.nextInt();
    input.nextLine();
    
    // 게시글 번호로 객체를 찾는다.
    int index = indexOfBoard(no);
    /*
    Board board = null;
    for (int i = 0; i < this.boardList.size(); i++) {
      Board temp = this.boardList.get(i);
      if (temp.getNo() == no) {
        board = temp;
        break;
      }
    }
    */
    // Board board = this.boardList.get(index);
    
    if (index/*board*/ == -1/*null*/) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    
    Board board = this.boardList.get(index); // 추가
    System.out.printf("번호: %d\n", board.getNo());
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("등록일: %s\n", board.getDate());
    System.out.printf("조회수: %d\n", board.getViewCount());
  }
  
  public void updateBoard() {
    System.out.print("번호? ");
    int no/*index*/ = input.nextInt();
    input.nextLine();
    
    // 게시글 번호로 객체를 찾는다.
    int index = indexOfBoard(no);
    /* 위의 메서드 이용
    Board oldBoard = null;
    int index = -1; // 배열 기본값
    for (int i = 0; i < this.boardList.size(); i++) {
      Board temp = this.boardList.get(i);
      if (temp.getNo() == no) {
        oldBoard = temp;
        index = i; // 아래의 this.boardList.set() 메서드 실행을 위해 index의 값을 줌
        break;
      }
    }
    */
    //Board oldBoard = this.boardList.get(index);
    
    if (index == -1/*oldBoard == null*/) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    
    // 유효한 인덱스라면 원래 내용을 보여주고 새 내용을 받도록 함
    Board oldBoard = this.boardList.get(index); // 추가
    
    System.out.printf("내용(%s)? ", oldBoard.getTitle());
    String title = input.nextLine();
    
    // 내용을 새로 입력하지 않으면(빈 문자열이면)
    if (title.length() == 0) {
      System.out.println("게시글 변경을 취소했습니다.");
      return;
    }
    
    Board newBoard = new Board();

    newBoard.setNo(oldBoard.getNo());
    newBoard.setViewCount(oldBoard.getViewCount());
    newBoard.setTitle(title);
    newBoard.setDate(new Date(System.currentTimeMillis()));
    
    this.boardList.set(index, newBoard);
    System.out.println("게시글을 변경했습니다.");
  }
  
  public void deleteBoard() {
    System.out.println("번호? ");
    int no/*index*/ = input.nextInt();
    input.nextLine();
    
    // 게시글 번호로 객체를 찾는다.
    int index = indexOfBoard(no);
    /*
    Board board = null;
    int index = -1; // 배열 기본값
    for (int i = 0; i < this.boardList.size(); i++) {
      Board temp = this.boardList.get(i);
      if (temp.getNo() == no) {
        board = temp;
        index = i; // 아래의 this.boardList.set() 메서드 실행을 위해 index의 값을 줌
        break;
      }
    }
    */
    //Board board = this.boardList.get(index);
    
    if (index == -1/*board == null*/) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    
    this.boardList.remove(index);
    
    System.out.println("게시글을 삭제했습니다.");
  }
  
  private int indexOfBoard(int no) {
    for (int i = 0; i < this.boardList.size(); i++) {
      //Board temp = this.boardList.get(i); 임시 변수 제거
      if (this.boardList.get(i).getNo() == no) {
        return i;
      }
  }
    return -1;
  }
}