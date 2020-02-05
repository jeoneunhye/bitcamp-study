package com.eomcs.lms.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Prompt;

public class MemberUpdateCommand implements Command {
  // List<Member> memberList;
  Prompt prompt;

  ObjectOutputStream out;
  ObjectInputStream in;

  public MemberUpdateCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt/*, List<Member> list*/) {
    // this.memberList = list;
    this.prompt = prompt;

    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {
    try {
      // 먼저 "/member/detail"을 서버에서 처리하여 입력한 번호의 데이터를 가져온다.
      int no = prompt.inputInt("번호? ");

      out.writeUTF("/member/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();  // server: out.writeUTF("OK" 또는 "FAIL");

      // server의 else문
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        // server: out.writeUTF("해당 번호의 회원이 없습니다.");
        return;
      }

      Member oldMember = (Member) in.readObject(); // 통째로 읽어 member 객체에 넣는다.
      // 여기까지 "/member/detail"의 코드와 같다.

      Member newMember = new Member();

      newMember.setNo(oldMember.getNo());
      newMember.setRegisteredDate(oldMember.getRegisteredDate());

      newMember.setName(
          prompt.inputString(String.format("이름(%s)? ", oldMember.getName()), oldMember.getName()));

      newMember.setEmail(prompt.inputString(String.format("이메일(%s)? ", oldMember.getEmail()),
          oldMember.getEmail()));

      newMember.setPassword(prompt.inputString(String.format("암호(%s)? ", oldMember.getPassword()),
          oldMember.getPassword()));

      newMember.setPhoto(prompt.inputString(String.format("사진(%s)? ", oldMember.getPhoto()),
          oldMember.getPhoto()));

      newMember.setTel(
          prompt.inputString(String.format("전화(%s)? ", oldMember.getTel()), oldMember.getTel()));

      if (oldMember.equals(newMember)) {
        System.out.println("회원 변경을 취소하였습니다.");
        return;
      }
      out.writeUTF("/member/update");
      out.writeObject(newMember);
      out.flush();

      response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      System.out.println("회원을 변경했습니다.");

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}