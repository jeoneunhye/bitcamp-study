// Java I/O API 사용하기 - serialize와 transient 변경자, 한정자
package com.eomcs.io.ex09.e;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Exam0520 {
  public static void main(String[] args) throws Exception {
    FileInputStream fileIn = new FileInputStream("temp/test12.data");
    BufferedInputStream bufIn = new BufferedInputStream(fileIn);
    ObjectInputStream in = new ObjectInputStream(bufIn);

    Score s = (Score) in.readObject();
    // 주의!
    // => DeSerialize를 통해 인스턴스가 생성될 때는 생성자가 호출되지 않는다.
    in.close();

    // => transient 변수의 값은 출력되지 않았기 때문에 읽을 수 없다.
    System.out.println(s); // sum=0, aver=0.00 오류 발생은 X

    // => transient 값은 인스턴스를 준비한 후 별도의 메서드를 통해 다시 계산해야 한다.
    s.compute();
    System.out.println(s); // sum=271, aver=90.333336

    // 아니 그냥 sum, aver 변수의 값을 파일에 저장한 후 읽으면 되지,
    // 왜 이렇게 번거롭게 다시 계산하게 만드는가?
    // => 조금이라도 데이터 왜곡을 방지하기 위함이다.
    // => 그러니 번거롭다 생각말고 다른 인스턴스 변수의 값으로 계산한 결과는
    //    가능한 출력하지 말고, 읽을 때 다시 계산하도록 하라!
  }
}