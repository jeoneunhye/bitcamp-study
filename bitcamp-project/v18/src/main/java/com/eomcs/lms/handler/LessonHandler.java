package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.ArrayList;

public class LessonHandler {
  ArrayList<Lesson> lessonList;
  public Scanner input;

  public LessonHandler(Scanner input) {
    this.input = input;
    lessonList = new ArrayList<>();
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
    // 수업 객체 목록을 복사받을 배열을 준비하고 toArray()를 실행한다.
    // toArray()의 리턴 값은 파라미터로 넘겨준 배열의 주소와 같다.
    Lesson[] arr = this.lessonList.toArray(new Lesson[this.lessonList.size()]);
    // Lesson/*Object*/[] arr = this.lessonList.toArray(Lesson[].class);
    for (Lesson l/*Object obj*/ : arr) {
      // Lesson l = (Lesson)obj;
      System.out.printf("%d, %s, %s ~ %s, %d\n",
          l.getNo(), l.getTitle(), l.getStartDate(), l.getEndDate(), l.getTotalHours());
    }
  }
}