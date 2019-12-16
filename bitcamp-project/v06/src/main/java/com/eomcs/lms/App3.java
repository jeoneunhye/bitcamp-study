package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

// 고급
// 1) 배열의 개수를 변수에 저장하여 크기 변경을 쉽게 하기 -> size라는 변수를 생성
// 2) 배열의 개수를 저장하고 있는 변수를 함부로 변경하지 못하도록 한다. -> 변수 앞에 final을 선언
// 3) 코드를 관리하기 쉽도록 작은 기능 단위로 분리한다. : 메서드
// 4) 메서드 사이에 공유하는 변수는 클래스 변수로 선언한다. : 스태틱 변수
// 5) 복합 데이터를 저장할 메모리를 설계한다. : 클래스 Board로 설계도를 생성
public class App3 { // 관련 메서드의 묶음
  // static을 앞에 붙여 변수들을 공유
  static final int size = 5734;

  static Board[] boards = new Board[size];  // size(식판)만큼 담기 위한 배열(배식카)을 준비
  
  static int count = 0;  // count 초기화
  
  public static void main(String[] args) {
    
    inputBoards();

    System.out.println();

    printBoards();  // 호출 명령어
  }
  
  static void inputBoards() {
    Scanner keyboard = new Scanner(System.in);  // local 변수
    String response;    // inputBoards에서만 사용하는 변수(local 변수)이므로 안에 넣어준다.
    
    for (int i = 0; i < size; i++) {
      Board b = new Board();  // Board 설계도에 따라 b라는 메모리를 준비하여 리턴
      
      System.out.print("번호? ");
      b.no = keyboard.nextInt();
      keyboard.nextLine(); // 줄바꿈 기호 제거용

      System.out.print("내용? ");
      b.title = keyboard.nextLine();

      b.date = new Date(System.currentTimeMillis());
      b.viewCount = 0;
      
      boards[i] = b;
      
      count++;

      System.out.println("계속 입력하시겠습니까?(Y/n)");
      response = keyboard.nextLine();
      if (!response.equalsIgnoreCase("y")) {    // 맨 앞 !삽입 : y가 아니라면. ignore case 대소문자구분X
        break;
      }
    }
    keyboard.close();   // 반복문이 끝나고 배치
  }
  
  static void printBoards() {
    for (int i = 0; i < count; i++) {
      Board b = boards[i];
      System.out.printf("%d, %s, %s, %d\n", b.no, b.title, b.date, b.viewCount);
      
    }
  }
}
