// 객체 읽기 - 파일의 데이터를 읽어 인스턴스로 만들기
package com.eomcs.io.ex05;

import java.io.FileInputStream;

public class Exam0120 {
  public static void main(String[] args) throws Exception {
    FileInputStream in = new FileInputStream("temp/test4.data");

    Member member = null;

    member = new Member();

    // 1) 이름 읽기
    int size = in.read(); // 이름이 저장된 바이트 배열의 수
    byte[] buf = new byte[size];
    in.read(buf, 0, size); // 이름 배열 갯수만큼 바이트를 읽어 배열에 저장한다.
    member.name = new String(buf, "UTF-8"); // (바이트배열, 문자코드표) 범위 지정 않고 통채로도 가능

    // 2) 나이(int) 읽기
    member.age = in.read() << 24;
    member.age += in.read() << 16;
    member.age += in.read() << 8;
    member.age += in.read();

    // 3) 성별 읽기
    if (in.read() == 1)
      member.gender = true;
    else
      member.gender = false;

    in.close();

    System.out.printf("%s\n", member); // Member [name=AB가각간, age=27, gender=true]
    // Member 클래스에서 toString() 오버라이딩한 대로 출력됨
  }
}