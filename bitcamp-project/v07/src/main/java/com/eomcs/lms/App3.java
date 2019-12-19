package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

// 입력 카운트하기
public class App3 {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    // 게시글 데이터를 저장할 메모리를 설계한다.
    class Board {
      int no;
      String title; // 사용 가능한 변수가 아님
      Date date;
      int viewCount;
    }
    
    final int SIZE = 100;
    
    Board[] boards = new Board[SIZE];
    // Board 레퍼런스 배열에 인스턴스의 주소를 담을 members 레퍼런스 배열 생성(준비)

//    for (int i = 0; i < SIZE; i++) {          -> Board board = new Board();
//      boards[i] = new Board();                -> boards[i] = board;
//    }
    // Board 인스턴스를 생성하여 레퍼런스 배열에 저장한다.

    int[] no = new int[SIZE];
    String[] title = new String[SIZE];
    Date[] date = new Date[SIZE];
    int[] viewCount = new int[SIZE];
    String response;

    int count = 0;
    for (int i = 0; i < SIZE; i++) {
      
      //Board board = boards[i];
      // boards[i].no, boards[i].title과 같이 대괄호를 매번 쓰지 않기 위해
      // board라는 변수를 준비하여 아래와 같이 변수명을 간결화한다(실무)
      
      Board board = new Board();
      
      System.out.print("번호? ");
      board.no = keyboard.nextInt();
      keyboard.nextLine(); // 줄바꿈 기호 제거용

      System.out.print("내용? ");
      board.title = keyboard.nextLine();

      board.date = new Date(System.currentTimeMillis());
      board.viewCount = 0;
      
      boards[i] = board;
      // 게시물 데이터가 보관된 Board 인스턴스의 주소를 레퍼런스 배열에 저장한다
      // 쓸 데 없이 100개를 만들지 않고 사용한 만큼만 만들어지도록
      count++;

      System.out.println("계속 입력하시겠습니까?(Y/n)");
      response = keyboard.nextLine();
      if (!response.equalsIgnoreCase("y")) {    // y가 아니라면
        break;
      }
    }
    keyboard.close();

    System.out.println();

    for (int i = 0; i < count; i++) {
      
      Board board = boards[i];  // 간결화
      
      System.out.printf("%d, %s, %s, %d\n",
          board.no, board.title, board.date, board.viewCount);
    }
  }
}
