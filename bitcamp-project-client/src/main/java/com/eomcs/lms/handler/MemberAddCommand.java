package com.eomcs.lms.handler;

import java.io.IOException;
// "/board/add" 명령어 처리
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Prompt;

public class MemberAddCommand implements Command {
  // List<Member> memberList;
  Prompt prompt;

  ObjectOutputStream out;
  ObjectInputStream in;

  // 클라이언트는 목록을 관리하지 않기 때문에
  // 서버와 대화할 수 있는 입출력 스트림을 넘겨 받는다!
  // DI(의존성 주입): 외부에서 의존 객체(dependency)를 주입(injection)받는다.
  public MemberAddCommand(ObjectOutputStream out, ObjectInputStream in,
      Prompt prompt/*List<Member> list*/) {
    // this.memberList = list;
    this.prompt = prompt;

    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {
    Member member = new Member();

    member.setNo(prompt.inputInt("번호? "));
    member.setName(prompt.inputString("이름? "));
    member.setEmail(prompt.inputString("이메일? "));
    member.setPassword(prompt.inputString("암호? "));
    member.setPhoto(prompt.inputString("사진? "));
    member.setTel(prompt.inputString("전화? "));
    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    // memberList.add(member);
    try {
      out.writeUTF("/member/add");

      out.writeObject(member); // writeObject는 내부적으로 객체를 cache한다.
      // list 안에 새로운 객체가 추가됐으나 list 객체 자체는
      // 변한 게 없기 때문에 Serialize를 또 하지 않고 기존의 list 객체를 출력
      // 실행 속도를 높이기 위한 자체 기능이다.
      out.flush(); // NIC의 데이터가 서버로 방출됨

      String response = in.readUTF(); // Server: out.writeUTF("OK" 또는 "FAIL");

      // Server의 else문
      if (response.equals("FAIL")) { // Server: out.writeUTF("FAIL");
        System.out.println(in.readUTF());
        // Server: out.wirteUTF("요청한 명령을 처리하지 못했습니다.");
        return;
      }

      System.out.println("회원을 저장하였습니다.");

    } catch (IOException e) {
      System.out.println("통신 오류 발생!");
    }
  }
}