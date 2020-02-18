package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;

public class LessonHandler {

  ArrayList/*LessonList*/ lessonList;
  public Scanner input;

  public LessonHandler(Scanner input) {
    this.input = input;
    lessonList = new ArrayList/*LessonList*/();
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

    lessonList.add(lesson);
    System.out.println("저장하였습니다.");
  }

  public void listLesson() {
    Object[] arr/*Lesson[] lessons*/ = this.lessonList.toArray();
    for (Object obj : arr/*Lesson l : lessons*/) {
      Lesson l = (Lesson)obj;   // 형변환 추가!
      System.out.printf("%d, %s, %s ~ %s, %d\n",
          l.getNo(), l.getTitle(), l.getStartDate(), l.getEndDate(), l.getTotalHours());
    }
  }
}