package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;

public class LessonHandler {
  
  // 인스턴스 필드 = 논-스태틱 필드
  // 각 수업 목록을 개별적으로 관리
  // => new 명령을 통해 생성된다.
  int lessonCount = 0;
  Lesson[] lessons/* = new Lesson[LESSON_SIZE]*/;
  
  // 클래스 필드 = 스태틱 필드
  public /*static*/ Scanner input;
  static final int LESSON_SIZE = 100;
  
  public LessonHandler(Scanner input) {
    this.input = input; // 생성자에서 Scanner를 반드시 받도록
    this.lessons = new Lesson[LESSON_SIZE];
  }
  
  public void addLesson() {
    Lesson lesson = new Lesson();

    System.out.print("번호? ");    
    lesson.no = input.nextInt();
    input.nextLine();    
    System.out.print("수업명? ");
    lesson.title = input.nextLine();
    System.out.print("수업내용? ");
    lesson.description = input.nextLine();
    System.out.print("시작일? ");
    lesson.startDate = Date.valueOf(input.next());
    System.out.print("종료일? ");
    lesson.endDate = Date.valueOf(input.next());
    System.out.print("총수업시간? ");
    lesson.totalHours = input.nextInt();
    System.out.print("일수업시간? ");
    lesson.dayHours = input.nextInt();
    input.nextLine(); 

    this.lessons[this.lessonCount++] = lesson;
    System.out.println("저장하였습니다.");
  }

  public void listLesson() {
    for (int i = 0; i < this.lessonCount; i++) {
      Lesson l = this.lessons[i];
      System.out.printf("%d, %s, %s ~ %s, %d\n",
          l.no, l.title, l.startDate, l.endDate, l.totalHours);
    }
  }
}
