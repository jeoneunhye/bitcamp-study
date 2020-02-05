package com.eomcs.lms.handler;

import java.io.IOException;
// "/board/add" 명령어 처리
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardAddCommand implements Command {
  // List<Board> boardList;
  Prompt prompt;

  ObjectOutputStream out;
  ObjectInputStream in;

  public BoardAddCommand(ObjectOutputStream out, ObjectInputStream in,
      Prompt prompt/*List<Board> list*/) {
    // this.boardList = list;
    this.prompt = prompt;

    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {
    Board board = new Board();

    board.setNo(prompt.inputInt("번호? "));
    board.setTitle(prompt.inputString("내용? "));
    board.setDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);
    board.setWriter(prompt.inputString("작성자? "));

    // boardList.add(board);
    try {
      out.writeUTF("/board/add");

      out.writeObject(board); // writeObject는 내부적으로 객체를 cache한다.
      // list 안에 새로운 객체가 추가됐으나 list 객체 자체는
      // 변한 게 없기 때문에 Serialize 또 하지 않고 기존의 list 객체를 출력
      // 실행 속도를 높이기 위한 자체 기능임
      // 다시 연결하면 새로운 cache가
      out.flush(); // NIC의 데이터가 서버로 방출됨

      String response = in.readUTF(); // Server: out.writeUTF("OK" 또는 "FAIL");

      // Server: else문
      if (response.equals("FAIL")) { // Server: out.writeUTF("FAIL");
        System.out.println(in.readUTF());
        // Server: out.wirteUTF("요청한 명령을 처리하지 못했습니다.");
        return;
      }

      System.out.println("게시물을 저장하였습니다.");

    } catch (IOException e) {
      System.out.println("통신 오류 발생!");
    }
  }
}