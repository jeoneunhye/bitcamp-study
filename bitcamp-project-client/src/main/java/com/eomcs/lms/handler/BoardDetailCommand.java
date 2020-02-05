package com.eomcs.lms.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardDetailCommand implements Command {
  // List<Board> boardList;

  Prompt prompt;

  ObjectOutputStream out;
  ObjectInputStream in;

  public BoardDetailCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt/*, List<Board> list*/) {
    // this.boardList = list;
    this.out = out;
    this.in = in;

    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      // 명령 창에서 먼저 번호를 입력받음
      int no = prompt.inputInt("번호? ");

      out.writeUTF("/board/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF(); // server: out.writeUTF("OK" 또는 "FAIL");

      // server의 else문
      if (response.equals("FAIL")) { // server: out.writeUTF("FAIL");
        System.out.println(in.readUTF());
        // server: out.writeUTF("해당 번호의 게시물이 없습니다.");
        return;
      }

      Board board = (Board) in.readObject(); // 통째로 읽어 board 객체에 넣는다.
      System.out.printf("번호: %d\n", board.getNo());
      System.out.printf("제목: %s\n", board.getTitle());
      System.out.printf("등록일: %s\n", board.getDate());
      System.out.printf("조회수: %d\n", board.getViewCount());
      System.out.printf("작성자: %s\n", board.getWriter());

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

    Board board = this.boardList.get(index);
    System.out.printf("번호: %d\n", board.getNo());
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("등록일: %s\n", board.getDate());
    System.out.printf("조회수: %d\n", board.getViewCount());
    System.out.printf("작성자: %s\n", board.getWriter());
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