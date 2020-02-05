package com.eomcs.lms.handler;

import java.io.IOException;
// "/board/add" 명령어 처리
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Prompt;

public class LessonAddCommand implements Command {
  // List<Member> memberList;
  Prompt prompt;

  ObjectOutputStream out;
  ObjectInputStream in;

  public LessonAddCommand(ObjectOutputStream out, ObjectInputStream in,
      Prompt prompt/*List<Member> list*/) {
    // this.memberList = list;
    this.prompt = prompt;

    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {
    Lesson lesson = new Lesson();

    lesson.setNo(prompt.inputInt("번호? "));
    lesson.setTitle(prompt.inputString("수업명? "));
    lesson.setDescription(prompt.inputString("설명? "));
    lesson.setStartDate(prompt.inputDate("시작일? "));
    lesson.setEndDate(prompt.inputDate("종료일? "));
    lesson.setTotalHours(prompt.inputInt("총수업시간? "));
    lesson.setDayHours(prompt.inputInt("일수업시간? "));

    // lessonList.add(lesson);
    try {
      out.writeUTF("/lesson/add");

      out.writeObject(lesson); // writeObject는 내부적으로 객체를 cache한다.
      // list 안에 새로운 객체가 추가됐으나 list 객체 자체는
      // 변한 게 없기 때문에 Serialize를 또 하지 않고 기존의 list 객체를 출력
      // 실행 속도를 높이기 위한 자체 기능이다.
      out.flush(); // NIC의 데이터가 서버로 방출됨

      String response = in.readUTF(); // Server: out.writeUTF("OK" 또는 "FAIL");

      // Server: else문
      if (response.equals("FAIL")) { // Server: out.writeUTF("FAIL");
        System.out.println(in.readUTF());
        // Server: out.wirteUTF("요청한 명령을 처리하지 못했습니다.");
        return;
      }

      System.out.println("수업 데이터를 저장하였습니다.");

    } catch (IOException e) {
      System.out.println("통신 오류 발생!");
    }
  }
}