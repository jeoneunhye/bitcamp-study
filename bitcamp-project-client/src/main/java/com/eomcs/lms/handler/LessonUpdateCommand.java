package com.eomcs.lms.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Prompt;

public class LessonUpdateCommand implements Command {
  // List<Lesson> lessonList;
  Prompt prompt;

  ObjectOutputStream out;
  ObjectInputStream in;

  public LessonUpdateCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt/*, List<Lesson> list*/) {
    // this.lessonList = list;
    this.prompt = prompt;

    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {
    try {
      // 먼저 "/lesson/detail"을 서버에서 처리하여 입력한 번호의 데이터를 가져온다.
      int no = prompt.inputInt("번호? ");

      out.writeUTF("/board/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();  // server: out.writeUTF("OK" 또는 "FAIL");

      // server의 else문
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        // server: out.writeUTF("해당 번호의 수업이 없습니다.");
        return;
      }

      Lesson oldLesson = (Lesson) in.readObject(); // 통째로 읽어 lesson 객체에 넣는다.
      // 여기까지 "/lesson/detail"의 코드와 같다.

      Lesson newLesson = new Lesson();

      newLesson.setNo(oldLesson.getNo());

      newLesson.setTitle(prompt.inputString(String.format("수업명(%s)? ", oldLesson.getTitle()),
          oldLesson.getTitle()));

      newLesson.setDescription(prompt.inputString("설명? ", oldLesson.getTitle()));

      newLesson.setStartDate(prompt.inputDate(String.format("시작일(%s)? ", oldLesson.getStartDate()),
          oldLesson.getStartDate()));

      newLesson.setEndDate(prompt.inputDate(String.format("종료일(%s)? ", oldLesson.getEndDate()),
          oldLesson.getEndDate()));

      newLesson.setTotalHours(prompt.inputInt(
          String.format("총수업시간(%d)? ", oldLesson.getTotalHours()), oldLesson.getTotalHours()));

      newLesson.setDayHours(prompt.inputInt(String.format("일수업시간(%d)? ", oldLesson.getDayHours()),
          oldLesson.getDayHours()));

      if (oldLesson.equals(newLesson)) {
        System.out.println("수업 변경을 취소하였습니다.");
        return;
      }

      out.writeUTF("/lesson/update");
      out.writeObject(newLesson);
      out.flush();

      response = in.readUTF(); // server: out.writeUTF("OK" 또는 "FAIL");
      if (response.equals("FAIL")) { // server: out.writeUTF("FAIL");
        System.out.println(in.readUTF()); // server: out.writeUTF("해당 번호의 수업이 없습니다.");
        return;
      }

      System.out.println("수업을 변경했습니다.");

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }

  /*
    int index = indexOfBoard(prompt.inputInt("번호? "));

    if (index == -1) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    Board oldBoard = this.boardList.get(index);
    Board newBoard = new Board();

    newBoard.setNo(prompt.inputInt(String.format("번호(%d)? ", oldBoard.getNo()),
        oldBoard.getNo()));

    newBoard.setTitle(prompt.inputString(String.format("내용(%s)? ", oldBoard.getTitle()),
        oldBoard.getTitle()));

    newBoard.setDate(oldBoard.getDate());
    newBoard.setViewCount(oldBoard.getViewCount());
    newBoard.setWriter(oldBoard.getWriter());

    if (oldBoard.equals(newBoard)) {
      System.out.println("게시글 변경을 취소하였습니다.");
      return;
    }

    this.boardList.set(index, newBoard);
    System.out.println("게시글을 변경했습니다.");
  }

  private int indexOfBoard(int no) {
    for (int i = 0; i < this.boardList.size(); i++) {
      if (this.boardList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
   */
}