package com.eomcs.lms.handler;
// "/lesson/add" 명령어 처리
import java.util.List;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Prompt;

public class LessonAddCommand implements Command {
  Prompt prompt;
  List<Lesson> lessonList;

  public LessonAddCommand(Prompt prompt, List<Lesson> list) {
    this.prompt = prompt;
    this.lessonList = list;
  }

  @Override
  public void execute() {
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
}