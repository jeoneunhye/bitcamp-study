package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardHandler {

BoardList boardList;
  Scanner input;
  
  public BoardHandler(Scanner input) {
    this.input = input;
    boardList = new BoardList();
  //  this.boards = new Board[BOARD_SIZE]; 제거 -> BoardList에서 boards 배열 생성
  }

  public BoardHandler(Scanner input, int capacity) {
    this.input = input;
    boardList = new BoardList(capacity);
  //  if (capacity < BOARD_SIZE || capacity < 10000) 제거 -> BoardList에서 boards 배열 생성
  //  this.boards = new Board[BOARD_SIZE];
  //  this.boards = new Board[capacity];
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

    // this.boards[this.boardCount++] = board;
    boardList.add(board);
    
    System.out.println("저장하였습니다.");
  }

  public void listBoard() {
    Board[] boards = boardList.toArray();
    // Board 배열 저장된 만큼 주세요
    
    for (Board b : boards) {
    // for (int i = 0; i < this.boardCount; i++) {
    // Board b = this.boards[i];
      System.out.printf("%d, %s, %s, %d\n",
          b.getNo(), b.getTitle(), b.getDate(), b.getViewCount());
    }
  }

  public void detailBoard() {
    System.out.println("게시물 번호? ");
    int no = input.nextInt();
    input.nextLine();
    
    Board board = boardList.get(no);
// --BoardList.get(); 로 이동
//    Board board = null;
//    for (int i = 0; i < this.boardCount; i++) {
//      if (this.boards[i].getNo() == no) {
//        board = this.boards[i];
//        break;
//      }
//    }
    if (board == null) {
      System.out.println("게시물 번호가 유효하지 않습니다.");
      return;
    }
    System.out.printf("번호: %d\n", board.getNo());
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("등록일: %s\n", board.getDate());
    System.out.printf("조회수: %d\n", board.getViewCount());
  }
}