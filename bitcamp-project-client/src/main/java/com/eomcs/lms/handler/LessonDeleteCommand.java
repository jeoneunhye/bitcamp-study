package com.eomcs.lms.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.util.Prompt;

public class LessonDeleteCommand implements Command {
  // List<Lesson> lessonList;
  Prompt prompt;

  ObjectOutputStream out;
  ObjectInputStream in;

  // 클라이언트는 목록을 관리하지 않기 때문에
  // 서버와 대화할 수 있는 입출력 스트림을 넘겨 받는다!
  // DI(의존성 주입): 외부에서 의존 객체(dependency)를 주입(injection)받는다.
  public LessonDeleteCommand(ObjectOutputStream out, ObjectInputStream in,
      Prompt prompt/*, List<Lesson> list*/) {
    // this.boardList = list;
    this.prompt = prompt;

    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      out.writeUTF("/lesson/delete");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF(); // server: out.writeUTF("OK" 또는 "FAIL");

      // server의 else문
      if (response.equals("FAIL")) { // server: out.writeUTF("FAIL");
        System.out.println(in.readUTF());
        // server: out.writeUTF("해당 번호의 수업이 없습니다.");
        return;
      }

      System.out.println("수업을 삭제했습니다.");

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}