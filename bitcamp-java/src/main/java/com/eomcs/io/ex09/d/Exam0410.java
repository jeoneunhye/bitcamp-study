// Java I/O API 사용하기 - java.io.Serializable 인터페이스를 구현한 클래스의 serialVersionUID
package com.eomcs.io.ex09.d;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Exam0410 {
  public static void main(String[] args) throws Exception {
    FileOutputStream fileOut = new FileOutputStream("temp/test11.data");
    BufferedOutputStream bufOut = new BufferedOutputStream(fileOut);
    ObjectOutputStream out = new ObjectOutputStream(bufOut);

    Member member = new Member();
    member.name = "AB가각간";
    member.age = 27;
    member.gender = true;

    // serialize하는 Member의 버전은 1280이다.
    out.writeObject(member);

    out.close();
    System.out.println("출력 완료!");
  }
}