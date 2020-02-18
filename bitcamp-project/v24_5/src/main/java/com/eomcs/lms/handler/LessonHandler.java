package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Iterator;
import com.eomcs.util.List;
import com.eomcs.util.Prompt;

public class LessonHandler {
  Prompt prompt;
  List<Lesson> lessonList;

  public LessonHandler(Prompt prompt, List<Lesson> list) {
    this.prompt = prompt;
    this.lessonList = list;
  }

  public void addLesson() {
    Lesson lesson = new Lesson();
    lesson.setNo(prompt.inputInt("번호? "));
    lesson.setTitle(prompt.inputString("수업명? "));
    lesson.setDescription(prompt.inputString("수업내용? "));
    lesson.setStartDate(prompt.inputDate("시작일? "));
    lesson.setEndDate(prompt.inputDate("종료일? "));
    lesson.setTotalHours(prompt.inputInt("총수업시간? "));
    lesson.setDayHours(prompt.inputInt("일수업시간? "));

    lessonList.add(lesson);
    System.out.println("저장하였습니다.");
  }

  public void listLesson() {
    Iterator<Lesson> iterator = lessonList.iterator();
    while (iterator.hasNext()) {
      Lesson l = iterator.next();

      System.out.printf("%d, %s, %s ~ %s, %d\n",
          l.getNo(), l.getTitle(), l.getStartDate(), l.getEndDate(), l.getTotalHours());
    }
  }

  public void detailLesson() {
    int index = indexOfLesson(prompt.inputInt("번호? "));
    if (index == -1) {
      System.out.println("해당 번호의 수업이 없습니다.");
      return;
    }

    Lesson lesson = this.lessonList.get(index);
    System.out.printf("번호: %d\n", lesson.getNo());
    System.out.printf("수업명: %s\n", lesson.getTitle());
    System.out.printf("수업내용: %s\n", lesson.getDescription());
    System.out.printf("기간: %s ~ %s\n",
        lesson.getStartDate(), lesson.getEndDate());
    System.out.printf("총수업시간: %d\n", lesson.getTotalHours());
    System.out.printf("일수업시간: %d\n", lesson.getDayHours());
  }

  public void updateLesson() {
    int index = indexOfLesson(prompt.inputInt("번호? "));
    if (index == -1) {
      System.out.println("해당 번호의 수업이 없습니다.");
      return;
    }

    Lesson oldLesson = this.lessonList.get(index);
    Lesson newLesson = new Lesson();

    newLesson.setNo(oldLesson.getNo());

    newLesson.setTitle(prompt.inputString(String.format("수업명(%s)? ", oldLesson.getTitle()),
        oldLesson.getTitle()));

    newLesson.setDescription(prompt.inputString(String.format("수업내용(%s)? ", oldLesson.getDescription())));

    newLesson.setStartDate(prompt.inputDate(String.format("시작일(%s)? ", oldLesson.getStartDate()),
        oldLesson.getStartDate()));

    newLesson.setEndDate(prompt.inputDate(String.format("종료일(%s)? ", oldLesson.getEndDate()),
        oldLesson.getEndDate()));

    newLesson.setTotalHours(prompt.inputInt(String.format("총수업시간(%d)? ", oldLesson.getTotalHours()),
        oldLesson.getTotalHours()));

    newLesson.setDayHours(prompt.inputInt(String.format("일수업시간(%d)? ", oldLesson.getDayHours()),
        oldLesson.getDayHours()));

    if (oldLesson.equals(newLesson)) {
      System.out.println("수업 변경을 취소하였습니다.");
      return;
    }

    this.lessonList.set(index, newLesson);
    System.out.println("수업을 변경했습니다.");
  }

  public void deleteLesson() {
    int index = indexOfLesson(prompt.inputInt("번호? "));
    if (index == -1) {
      System.out.println("해당 번호의 수업이 없습니다.");
      return;
    }

    this.lessonList.remove(index);
    System.out.println("수업을 삭제했습니다.");
  }

  private int indexOfLesson(int no) {
    for (int i = 0; i < this.lessonList.size(); i++) {
      if (this.lessonList.get(i).getNo() == no) {
        return i;
      }
    }
    
    return -1;
  }
}