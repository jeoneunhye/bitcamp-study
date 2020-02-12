package com.eomcs.lms.handler;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.eomcs.lms.domain.Board;

public class BoardListCommand implements Command {
  // List<Board> boardList;

  ObjectOutputStream out;
  ObjectInputStream in;

  // 클라이언트는 목록을 관리하지 않기 때문에
  // 서버와 대화할 수 있는 입출력 스트림을 넘겨 받는다!
  // DI(의존성 주입): 외부에서 의존 객체(dependency)를 주입(injection)받는다.
  public BoardListCommand(ObjectOutputStream out, ObjectInputStream in/*list<Board> list*/) {
    // this.boardList = list;

    this.out = out;
    this.in = in;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {
    try {
      // out.println("/board/list");
      out.writeUTF("/board/list");
      out.flush();
      // println()은 줄바꿈 코드가 있다. 그래서 자동으로 flush()를 수행하고
      // NIC(랜카드) 버퍼에 보관된 데이터가 전송된다.
      // ObjectOutputStream은 내부에서 임시 버퍼를 사용한다.
      // => 서버에 데이터를 즉시 전송하려면 개발자가 명시적으로 flush()를 호출하여
      // 버퍼에 저장된 내용을 내보내야 한다.
      // 호출하지 않으면 writeUTF()한 내용이 서버로 전송되지 않고 버퍼에 저장되어 있다.

      String response = in.readUTF(); // server: out.writeUTF("OK" 또는 "FAIL");

      if (response.equals("FAIL")) { // server: out.writeUTF("FAIL");
        System.out.println(in.readUTF());
        // server: out.writeUTF("요청한 명령을 처리하지 못했습니다.");
        return;
      }

      List<Board> boards = (List<Board>) in.readObject(); // server: out.writeObject(boards);

      for (Board b : boards) { // List에 담긴 Board 객체를 전부 꺼내어 데이터를 출력한다.
        System.out.printf("%d, %s, %s, %d, %s\n",
            b.getNo(), b.getTitle(), b.getDate(), b.getViewCount(), b.getWriter());
      }

    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
    }
  }
}