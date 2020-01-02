package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;

public class LessonHandler {

  //  int lessonCount = 0;
  //  Lesson[] lessons;
  LessonList lessonList;
  public Scanner input;
  //  static final int LESSON_SIZE = 100;

  public LessonHandler(Scanner input) {
    this.input = input;
    //    this.lessons = new Lesson[LESSON_SIZE];
    lessonList = new LessonList();
  }

  public void addLesson() {
    Lesson lesson = new Lesson();

    System.out.print("번호? ");    
    lesson.setNo(input.nextInt());
    input.nextLine();    
    System.out.print("수업명? ");
    lesson.setTitle(input.nextLine());
    System.out.print("수업내용? ");
    lesson.setDescription(input.nextLine());
    System.out.print("시작일? ");
    lesson.setStartDate(Date.valueOf(input.next()));
    System.out.print("종료일? ");
    lesson.setEndDate(Date.valueOf(input.next()));
    System.out.print("총수업시간? ");
    lesson.setTotalHours(input.nextInt());
    System.out.print("일수업시간? ");
    lesson.setDayHours(input.nextInt());
    input.nextLine(); 

    // this.lessons[this.lessonCount++] = lesson;
    lessonList.add(lesson);
    System.out.println("저장하였습니다.");
  }

  public void listLesson() {
    // for (int i = 0; i < this.lessonCount; i++) {
    // Lesson l = this.lessons[i];
    Lesson[] lessons = lessonList.toArray();
    for (Lesson l : lessons) {
      System.out.printf("%d, %s, %s ~ %s, %d\n",
          l.getNo(), l.getTitle(), l.getStartDate(), l.getEndDate(), l.getTotalHours());
    }
    // }
  }
}