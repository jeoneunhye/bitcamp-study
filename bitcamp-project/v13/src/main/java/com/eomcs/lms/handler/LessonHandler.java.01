package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;

public class LessonHandler {
//  static class Lesson {
//    int no;
//    String title;
//    String description;
//    Date startDate;
//    Date endDate;
//    int totalHours;
//    int dayHours;
//  }
  static final int LESSON_SIZE = 100;
  static int lessonCount = 0;
  static Lesson[] lessons = new Lesson[LESSON_SIZE];
  // 다른 패키지에 있는 클래스에서도 이 변수를 사용하게 하려면 공개해야 한다 (public)
  public static Scanner keyboard;
  
  public static void addLesson() {
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

  public static void listLesson() {
    for (int i = 0; i < lessonCount; i++) {
      Lesson l = lessons[i];
      System.out.printf("%d, %s, %s ~ %s, %d\n",
          l.no, l.title, l.startDate, l.endDate, l.totalHours);
    }
  }
}
