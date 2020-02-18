package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardHandler {

  ArrayList/*BoardList*/ boardList;
  Scanner input;
  
  public BoardHandler(Scanner input) {
    this.input = input;
    boardList = new ArrayList/*BoardList*/();
  }

  public BoardHandler(Scanner input, int capacity) {
    this.input = input;
    boardList = new ArrayList/*BoardList*/(capacity);
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
    
    System.out.println("저장하였습니다.");
  }

  public void listBoard() {
    Object/*Board*/[] arr/*boards*/ = this.boardList.toArray();
    
    for (Object obj : arr/*Board b : boards*/) {
      Board b = (Board)obj;
      // 형변환 추가! 사용 전에 obj가 가리키는 것이 Board라고 알려주는 것
      // 위 두 줄 변수 설정 잘하기
      System.out.printf("%d, %s, %s, %d\n",
          b.getNo(), b.getTitle(), b.getDate(), b.getViewCount());
    }
  }

  public void detailBoard() {
    System.out.println("게시물 인덱스? "); // 번호-> 인덱스로 변경 0부터 시작
    int index/*no*/ = input.nextInt();
    input.nextLine();
    
    Board board = (Board)this.boardList.get(index);
    // 추가! 꺼낸 것은 보드다 말해주는 것->형변환(typecasting)
    // 원래 데이터타입의 변수에 담아서 써야 한다.
    // Board board = 0boardList.get(no);
    
    if (board == null) {
      System.out.println("게시물 인덱스가 유효하지 않습니다.");
      return;
    }
    System.out.printf("번호: %d\n", board.getNo());
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("등록일: %s\n", board.getDate());
    System.out.printf("조회수: %d\n", board.getViewCount());
  }
}