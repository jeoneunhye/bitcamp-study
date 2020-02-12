package com.eomcs.lms.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.util.Prompt;

public class BoardDeleteCommand implements Command {
  // List<Board> boardList;
  Prompt prompt;

  ObjectOutputStream out;
  ObjectInputStream in;

  public BoardDeleteCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt
      /*, List<Board> list*/) {
    // this.boardList = list;
    this.prompt = prompt;

    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      out.writeUTF("/board/delete");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF(); // server: out.writeUTF("OK" 또는 "FAIL");

      // server의 else문
      if (response.equals("FAIL")) { // server: out.writeUTF("FAIL");
        System.out.println(in.readUTF());
        // server: out.writeUTF("해당 번호의 게시물이 없습니다.");
        return;
      }

      System.out.println("게시물을 삭제했습니다.");

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}