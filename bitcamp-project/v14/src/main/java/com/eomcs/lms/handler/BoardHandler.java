package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardHandler {

  // 인스턴스 필드 = non-static 필드
  // => new 명령을 실행해야만 생성되는 변수
  // => 개별적으로 관리되어야 하는 값일 경우 인스턴스 필드로 선언한다.
  // BoardHandler의 인스턴스다! 라고 파라미터를 사용하고 변수 앞에 붙여줘야 한다.
  // 인스턴스 주소로 변수 접근 : 인스턴스 주소.변수 = 값;
  Board[] boards/* = new Board[BOARD_SIZE]*/; // static 제거
  // ~> 배열을 아래 생성자에서 만들겠다!
  int boardCount = 0;
  // 인스턴스 필드의 존재 이유? 변수는 개별 관리 메서드는 공유
  // 같은 메서드인 배열을 여러 개 만들 때
  
  /*public static*/ Scanner input;
  // 이전까지 게시물 데이터를 입력받을 때 키보드에서 입력받았지만,
  // 향후 네트워크나 파일로부터도 입력받을 수 있다.
  // 게시판마다 입력받는 방식이 다를 수 있다.
  // 이런 경우를 대비하여 입력 데이터를 읽는 것을 keyboard로 한정하지 말자!
  // -> refactor-rename keyboard에서 input으로 변경
  // 또한 각 게시판마다 입력받는 방식을 다르게 할 수 있도록 인스턴스 변수로 선언하자.
  
  // BoardHandler를 실행하려면 데이터를 입력받는 도구가 반드시 있어야 한다.
  // 이런 도구를 "의존 객체(dependency object)"라 부른다.
  // 보통 "dependency"라 줄여서 부른다.
  // 생성자에서 해야할 일은 인스턴스를 생성할 때
  // 이런 의존 객체를 반드시 초기화시키도록 하는 것이다.
  
  // 클래스 필드 = static 필드
  // => Method Area에 클래스 코드가 로딩될 때 자동 생성된다. 클래스 당 한번만
  // => 공통으로 사용할 값일 경우 클래스 필드로 선언한다.
  // 클래스 이름으로 변수 접근 : 클래스 이름.변수 = 값;
  static final int BOARD_SIZE = 100;

  public BoardHandler(Scanner input) {
    this.input = input;
    this.boards = new Board[BOARD_SIZE];
  }
  
  // 생성자(Constructor)
  // => 인스턴스를 생성할 때 반드시 호출되는 메서드
  // => new 명령을 실행할 때 호출될 생성자를 지정할 수 있다.
  // => 의존 객체를 초기화시키는 코드를 넣는다.
  // => 생성자는 리턴값이 없고 클래스 이름과 같은 이름으로 메서드를 정의한다.
  public BoardHandler(Scanner input, int capacity) {// size
    this.input = input;
    if (capacity < BOARD_SIZE || capacity < 10000)
      this.boards = new Board[BOARD_SIZE]; // 기본값으로
    this.boards = new Board[capacity];
  }
  // 
  
  // 클래스 메서드
  // => 인스턴스 없이 호출하는 메서드이다.
  // => 인스턴스를 사용하려면 파라미터를 통해 호출할 때 외부에서 받아야 한다.

  // 인스턴스 메서드
  // => 인스턴스가 있어야만 호출할 수 있는 메서드이다.
  // => 인스턴스 필드를 사용하는 메서드는 인스턴스 메서드로 선언하라(static을 빼라)
  // => 호출할 때는 반드시 인스턴스 주소를 줘야 한다.
  //    인스턴스주소.메서드명();
  // => 이렇게 인스턴스의 변수 값을 다루는 메서드는
  //    "연산자(operation)"라 부를 수 있다.
  // => 내장 변수 this를 사용
  public /*static*/void addBoard(/*BoardHandler boardHandler*/) {
    Board board = new Board();

    System.out.print("번호? ");
    board.no = input.nextInt();
    input.nextLine();

    System.out.print("내용? ");
    board.title = input.nextLine();

    board.date = new Date(System.currentTimeMillis());
    board.viewCount = 0;

    /*boardHandlder.*/this.boards[/*boardHandlder.*/this.boardCount++] = board;
    System.out.println("저장하였습니다.");
  }

  public void listBoard() {
    for (int i = 0; i < this.boardCount; i++) {
      Board b = this.boards[i];
      System.out.printf("%d, %s, %s, %d\n",
          b.no, b.title, b.date, b.viewCount);
    }
  }

  public void detailBoard() {
    System.out.println("게시물 번호? ");
    int no = input.nextInt();
    input.nextLine();

    Board board = null;
    for (int i = 0; i < this.boardCount; i++) {
      if (this.boards[i].no == no) {
        board = this.boards[i];
        break;  // 가장 가까운 반복문 또는 조건문을 나간다
      }
    }
    if (board == null) {
      System.out.println("게시물 번호가 유효하지 않습니다.");
      return; // 밑의 문장 실행 안하고 호출한 위치로 즉시 돌아간다
    }
    System.out.printf("번호: %d\n", board.no);
    System.out.printf("제목: %s\n", board.title);
    System.out.printf("등록일: %s\n", board.date);
    System.out.printf("조회수: %d\n", board.viewCount);
  }
}