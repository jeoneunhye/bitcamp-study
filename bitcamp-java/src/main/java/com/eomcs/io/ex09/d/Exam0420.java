// Java I/O API 사용하기 - java.io.Serializable 인터페이스를 구현한 클래스의 serialVersionUID
package com.eomcs.io.ex09.d;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Exam0420 {
  public static void main(String[] args) throws Exception {
    FileInputStream fileIn = new FileInputStream("temp/test11.data");
    BufferedInputStream bufIn = new BufferedInputStream(fileIn);
    ObjectInputStream in = new ObjectInputStream(bufIn);

    // test1
    // Exam0410에서 Member 객체를 출력한다.
    // Member 클래스를 변경하지 않은 상태로 Exam0420에서 데이터를 읽는다.
    // => 결과: 정상적으로 읽을 수 있다.

    // test2
    // Exam0410에서 Member 객체를 출력한다.
    // Member 클래스에 'tel' 필드를 추가한다.
    // Exam0420에서 Member 데이터를 읽는다.
    // => 결과: 정상적으로 읽을 수 있다.

    // test3
    // Exam0410에서 Member 객체를 출력한다.
    // Member 클래스에서 'age' 필드를 제거한다.
    // Exam0420에서 Member 데이터를 읽는다.
    // => 결과: 정상적으로 읽을 수 있다.
    Member member = (Member) in.readObject();

    in.close();

    System.out.println(member);
  }
}

// 결론!
// Member 객체를 Serialize한 후
// 필드를 추가하거나 삭제하더라도
// serialVersionUID 값만 같으면
// JVM은 같은 형식으로 판단한다.