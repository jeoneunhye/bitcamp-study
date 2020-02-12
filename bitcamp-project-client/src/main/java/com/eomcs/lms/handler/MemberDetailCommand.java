package com.eomcs.lms.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Prompt;

public class MemberDetailCommand implements Command {
  // List<Member> memberList;
  Prompt prompt;

  ObjectOutputStream out;
  ObjectInputStream in;

  // 클라이언트는 목록을 관리하지 않기 때문에
  // 서버와 대화할 수 있는 입출력 스트림을 넘겨 받는다!
  // DI(의존성 주입): 외부에서 의존 객체(dependency)를 주입(injection)받는다.
  public MemberDetailCommand(ObjectOutputStream out, ObjectInputStream in,
      Prompt prompt/*, List<Member> list*/) {
    // this.memberList = list;
    this.out = out;
    this.in = in;

    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      // 명령 창에서 먼저 번호를 입력받음
      int no = prompt.inputInt("번호? ");

      out.writeUTF("/member/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF(); // server: out.writeUTF("OK" 또는 "FAIL");

      // server의 else문
      if (response.equals("FAIL")) { // server: out.writeUTF("FAIL");
        System.out.println(in.readUTF());
        // server: out.writeUTF("해당 번호의 회원이 없습니다.");
        return;
      }

      Member member = (Member) in.readObject(); // 통째로 읽어 Member 객체에 넣는다.

      System.out.printf("번호: %d\n", member.getNo());
      System.out.printf("이름: %s\n", member.getName());
      System.out.printf("이메일: %s\n", member.getEmail());
      System.out.printf("암호: %s\n", member.getPassword());
      System.out.printf("사진: %s\n", member.getPhoto());
      System.out.printf("전화: %s\n", member.getTel());

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}