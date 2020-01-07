package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;
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
    for (Lesson l : arr) {
      System.out.printf("%d, %s, %s ~ %s, %d\n",
          l.getNo(), l.getTitle(), l.getStartDate(), l.getEndDate(), l.getTotalHours());
    }
  }

  public void detailLesson() {
    System.out.println("수업 인덱스? ");
    int index = input.nextInt();
    input.nextLine();

    Lesson lesson = this.lessonList.get(index);

    if (lesson == null) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
      return;
    }
    System.out.printf("번호: %d\n", lesson.getNo());
    System.out.printf("수업명: %s\n", lesson.getTitle());
    System.out.printf("수업내용: %s\n", lesson.getDescription());
    System.out.printf("기간: %s ~ %s\n",
        lesson.getStartDate(), lesson.getEndDate());
    System.out.printf("총수업시간: %d\n", lesson.getTotalHours());
    System.out.printf("일수업시간: %d\n", lesson.getDayHours());
  }

  public void updateLesson() {
    System.out.print("수업 인덱스? ");
    int index = input.nextInt();
    input.nextLine();

    Lesson oldLesson = this.lessonList.get(index);

    if (oldLesson == null) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
      return;
    }

    System.out.printf("수업명? (%s)", oldLesson.getTitle());
    String title = input.nextLine();

    System.out.print("수업내용? ");
    String description = input.nextLine();

    System.out.printf("시작일(%s)? ", oldLesson.getStartDate());
    Date startDate = Date.valueOf(input.next());

    System.out.printf("종료일(%s)? ", oldLesson.getEndDate());
    Date endDate = Date.valueOf(input.next());
    System.out.printf("총수업시간(%d)? ", oldLesson.getTotalHours());
    int totalHours = input.nextInt();
    System.out.printf("일수업시간(%d)? ", oldLesson.getDayHours());
    int dayHours = input.nextInt();
    input.nextLine();

    Lesson newLesson = new Lesson();

    newLesson.setTitle(title);
    if (description.length() == 0) {
      newLesson.setDescription(oldLesson.getDescription());
    }
    newLesson.setDescription(description);
    newLesson.setStartDate(startDate);
    newLesson.setEndDate(endDate);
    newLesson.setTotalHours(totalHours);
    newLesson.setDayHours(dayHours);

    this.lessonList.set(index, newLesson);
    // 배열[index]에 newLesson 객체를 복사
    System.out.println("수업을 변경했습니다.");
  }

  public void deleteLesson() {
    System.out.println("수업 인덱스? ");
    int index = input.nextInt();
    input.nextLine();

    Lesson lesson = this.lessonList.get(index);

    if (lesson == null) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
      return;
    }

    lessonList.remove(index);
    System.out.println("수업을 삭제했습니다.");
  }
}