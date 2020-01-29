// 객체 출력 - 인스턴스의 값을 출력
package com.eomcs.io.ex05;

import java.io.FileOutputStream;

public class Exam0110 {
  public static void main(String[] args) throws Exception {
    FileOutputStream out = new FileOutputStream("temp/test4.data");

    Member member = new Member();
    member.name = "AB가각간";
    member.age = 27;
    member.gender = true;

    // 인스턴스의 값을 출력하라!
    // 1) 이름 출력
    byte[] bytes = member.name.getBytes("UTF-8"); // 문자열을 byte 배열로 변환
    out.write(bytes.length); // OB 1바이트만큼을 이용해 이름의 length를 알려줌 'AB가각간은 11바이트다.'
    out.write(bytes); // 41 42 EA B0 80 EA B0 81 EA B0 84 문자열 바이트

    // 2) 나이 출력 (4바이트)
    out.write(member.age >> 24); // 00
    out.write(member.age >> 16); // 00
    out.write(member.age >> 8); //00
    out.write(member.age); //1B

    // 3) 성별 출력 (1바이트)
    if (member.gender)
      out.write(1); // gender가 true므로 01이 입력됨
    else
      out.write(0);

    out.close();

    System.out.println("데이터 출력 완료!");
  }
}