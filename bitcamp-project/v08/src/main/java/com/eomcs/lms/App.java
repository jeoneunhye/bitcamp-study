package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;
public class App {
  //레슨 목록와 멤버 목록 SIZE와 count 분리 필요
  static final int LESSON_SIZE = 100;

  static Scanner keyboard = new Scanner(System.in);   

  static int lessonCount = 0;

  static Lesson[] lessons = new Lesson[LESSON_SIZE];

  static class Lesson {
    int no;
    String title;
    String description;
    Date startDate;
    Date endDate;
    int totalHours;
    int dayHours;
  }
  public static void main(String[] args) {
    
    final int MEMBER_SIZE = 100;
    final int BOARD_SIZE = 100;
    int memberCount = 0;
    int boardCount = 0;
    class Member {
      int no;
      String name;
      String email;
      String password;
      String photo;
      String tel;
      Date registeredDate;
    }
    Member[] members = new Member[MEMBER_SIZE];
    class Board {
      int no;
      String title;
      Date date;
      int viewCount;
    }
    Board[] boards = new Board[BOARD_SIZE];
    String command; // while문에서도 사용하기 때문에 밖에서 변수 선언
    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();    // 사용자로부터 한 줄을 입력받겠다
      switch (command) { //정수 문자 다 가능 단순값이기 때문에 if대신 사용
        case "/lesson/add":
          inputBoards();
          break;
        case "/lesson/list":
          printBoards();
          break;
        case "/member/add":
          Member member = new Member();
          System.out.print("번호? ");
          member.no = keyboard.nextInt();
          keyboard.nextLine();
          System.out.print("이름? ");
          member.name = keyboard.nextLine();
          System.out.print("이메일? ");
          member.email = keyboard.nextLine();
          System.out.print("암호? ");
          member.password = keyboard.nextLine();
          System.out.print("사진? ");
          member.photo = keyboard.nextLine();
          System.out.print("전화? ");
          member.tel = keyboard.nextLine();
          //System.out.print("가입일? ");
          member.registeredDate = new Date(System.currentTimeMillis());  // 현재 날짜에 대한 밀리초를 날짜 형식으로 변경
          
          members[memberCount++] = member;
          System.out.println("저장하였습니다.");
          break;
        case "/member/list":
          for (int i = 0; i < memberCount; i++) {
          Member m = members[i];
          System.out.printf("%d, %s, %s, %s, %s\n",
              m.no, m.name, m.email, m.tel, m.registeredDate);
       // ★변수명 member하면 데이터 못찾음!!!
          }
          break;
        case "/board/add":
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
          break;
        case "/board/list":
          for (int i = 0; i < boardCount; i++) {
            Board b = boards[i];
            System.out.printf("%d, %s, %s, %d\n",
                b.no, b.title, b.date, b.viewCount);
          }
          break;
        default:
          //System.out.println("실행할 수 없는 명령입니다.");
          // quit을 입력했을 때도 이 문구가 뜬다
          if (!command.equalsIgnoreCase("quit")) {
            System.out.println("실행할 수 없는 명령입니다.");
          }
      }
    } while (!command.equalsIgnoreCase("quit"));
    System.out.println("안녕!");
    // quit 명령이 입력되기 전까지 계속 입력받도록!
    // quit이 아니라면 do문을 반복하라
    // while문만 써서 command가 quit이라면 break;를 사용하는 방법도 가능하다
  }
  static void inputBoards() { 
    Lesson lesson = new Lesson();     // lesson[i] = lesson;로 입력한 만큼만 저장되게

    System.out.print("번호? ");    
    lesson.no = keyboard.nextInt();
    keyboard.nextLine();    
    System.out.print("수업명? ");
    lesson.title = keyboard.nextLine();
    System.out.print("수업내용? ");
    lesson.description = keyboard.nextLine();
    System.out.print("시작일? ");
    lesson.startDate = Date.valueOf(keyboard.next());
    System.out.print("종료일? ");
    lesson.endDate = Date.valueOf(keyboard.next());
    System.out.print("총수업시간? ");
    lesson.totalHours = keyboard.nextInt();
    System.out.print("일수업시간? ");
    lesson.dayHours = keyboard.nextInt();
    keyboard.nextLine(); 

    lessons[lessonCount++] = lesson;
    System.out.println("저장하였습니다.");
  }

  static void printBoards() {
    for (int i = 0; i < lessonCount; i++) {
      Lesson l = lessons[i];   // ★변수명 lesson하면 데이터 못찾음!!!
      System.out.printf("%d, %s, %s ~ %s, %d\n",
          l.no, l.title, l.startDate, l.endDate, l.totalHours);
    }
  }
}
