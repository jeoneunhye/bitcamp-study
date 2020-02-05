package com.eomcs.lms.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Prompt;

public class LessonDetailCommand implements Command {
  // List<Lesson> lessonList;

  Prompt prompt;

  ObjectOutputStream out;
  ObjectInputStream in;

  public LessonDetailCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt/*, List<Lesson> list*/) {
    // this.lessonList = list;
    this.out = out;
    this.in = in;

    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      // 명령 창에서 먼저 번호를 입력받음
      int no = prompt.inputInt("번호? ");

      out.writeUTF("/lesson/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF(); // server: out.writeUTF("OK" 또는 "FAIL");

      // server의 else문
      if (response.equals("FAIL")) { // server: out.writeUTF("FAIL");
        System.out.println(in.readUTF());
        // server: out.writeUTF("해당 번호의 수업이 없습니다.");
        return;
      }

      Lesson lesson = (Lesson) in.readObject(); // 통째로 읽어 Lesson 객체에 넣는다.

      System.out.printf("번호: %d\n", lesson.getNo());
      System.out.printf("수업명: %s\n", lesson.getTitle());
      System.out.printf("설명: %s\n", lesson.getDescription());
      System.out.printf("시작일: %s\n", lesson.getStartDate());
      System.out.printf("종료일: %s\n", lesson.getEndDate());
      System.out.printf("총수업시간: %d\n", lesson.getTotalHours());
      System.out.printf("일수업시간: %d\n", lesson.getDayHours());

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}