package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.App2.Member;
public class App {
  static final int SIZE = 100;
  
  static int[] no = new int[SIZE];
  static String[] title = new String[SIZE];
  static Date[] startDate = new Date[SIZE];
  static Date[] endDate = new Date[SIZE];
  static int[] totalHours = new int[SIZE];
  static int count = 0; // 최대 100개(size) 중 몇 개를 입력했는지 보기 위해
  static Lesson[] lessons = new Lesson[SIZE];
  // 강의 정보를 담을  메모리의 설계도를 만든다.
  // 클래스?
  // - 애플리케이션에서 다룰 특정 데이터(수업정보, 학생정보, 게시물, 제품정보 등)의
  //   메모리 구조를 설계하는 문법이다.
  // - 이렇게 개발자가 새롭게 정의한 데이터 타입을
  //   "사용자 정의 데이터 타입"이라 부른다.
  // - 즉 '클래스'는 '사용자 정의 데이터 타입'을 만들 때 사용하는 문법이다.
  static public class Lesson {
    int no;
    String title;
    String description;
    Date startDate;
    Date endDate;
    int totalHours;
    int dayHours;
  }
  public static void main(String[] args) {
    inputBoards();
    System.out.println();
    printBoards();
  }
   static void inputBoards() { 
    Scanner keyboard = new Scanner(System.in);   
    String[] description = new String[SIZE];
    int[] dayHours = new int[SIZE];
    
    String response;
    Lesson[] lessons = new Lesson[SIZE];  //SIZE만큼 레퍼런스를 만들겠다
    
    for (int i = 0; i < SIZE; i++) {
      lessons[i] = new Lesson();          //SIZE만큼 인스턴스를 만들겠다
    }
    
    for (int i = 0; i < SIZE; i++) {
      //Lesson lesson = lessons[i];   // 변수 간결화
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
      keyboard.nextLine();  // ★일수업시간 입력값 다음 남아있는 줄바꿈 값 제거
      
      // 수업 정보를 담고있는 인스턴스의 주소를 나중에 사용할 수 있도록
      // 레퍼런스 배열에 보관해둔다
      count++;
      lessons[i] = lesson;  // count++ 뒤에 와야 한다?
      
      System.out.println("계속 입력하시겠습니까?(Y/n)");
      response = keyboard.nextLine();
      if (!response.equalsIgnoreCase("y")) {    // response가 Y가 아니라면 break를 실행해라
        break;
      }
    }
    keyboard.close();
   }

    static void printBoards() {
    for (int i = 0; i < count; i++) {   // size(전체)가 아닌 count(입력된 갯수)까지 반복하라!
      Lesson lesson = lessons[i];   // 변수 간결화
    System.out.printf("%d, %s, %s ~ %s, %d\n",
        lesson.no, lesson.title, lesson.startDate, lesson.endDate, lesson.totalHours);
    }
  }
}
