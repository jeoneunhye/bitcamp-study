package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardHandler5 {
  //  static class Board {
  //    int no;
  //    String title;
  //    Date date;
  //    int viewCount;
  //    String writer;
  //  }
  static final int BOARD_SIZE = 100;
  static int boardCount = 0;
  static Board[] boards = new Board[BOARD_SIZE];
  public static Scanner keyboard;

  public static void addBoard() {
    Board board = new Board();

    System.out.print("번호? ");
    board.no = keyboard.nextInt();
    keyboard.nextLine(); // 줄바꿈 기호 제거용

    System.out.print("내용? ");
    board.title = keyboard.nextLine();

    board.date = new Date(System.currentTimeMillis());
    board.viewCount = 0;

    boards[boardCount++] = board;
    System.out.println("저장하였습니다.");
  }

  public static void listBoard() {
    for (int i = 0; i < boardCount; i++) {
      Board b = boards[i];
      System.out.printf("%d, %s, %s, %d\n",
          b.no, b.title, b.date, b.viewCount);
    }
  }

  public static void detailBoard() {
    System.out.println("게시물 번호? ");
    int no = keyboard.nextInt();
    // 번호 엔터(줄바꿈) 입력시 '실행할 수 없는 명령입니다.'
    // nextInt : 공백(whitespace)을 만날 때까지 읽는다.(공백 앞에서 끊음)
    // 입력한 문자 '1'을 숫자 1로 리턴하고 빈문자열을 받아 !command=quit이 됨
    keyboard.nextLine();    // 숫자 뒤의 남은 공백을 제거

    Board board = null;
    for (int i = 0; i < boardCount; i++) {
      if (boards[i].no == no) { // board의 no와 입력값 no가 같으면
        board = boards[i];  // 해당 게시글을 꺼내 board에 담아라
        break;  // for 반복문을 멈춰라
      }
    }
    if (board == null) {
      System.out.println("게시물 번호가 유효하지 않습니다.");
      return;
      // 리턴 타입이 void일 경우의 return;은 메서드 실행을 끝내겠다는 뜻
    }
    // Board board = boards[no];   // 배열은 0번부터 시작
    // 배열 인덱스는 게시판에 부적합.
    // 어떤 게시글이 삭제되면 번호가 밀려버리기 때문에 원 글을 조회할 수 없다
    System.out.printf("번호: %d\n", board.no);
    System.out.printf("제목: %s\n", board.title);
    System.out.printf("등록일: %s\n", board.date);
    System.out.printf("조회수: %d\n", board.viewCount);
  }
}

