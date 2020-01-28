// Character Stream - 문자 배열 출력하기
package com.eomcs.io.ex03;

import java.io.FileWriter;

public class Exam0210 {
  public static void main(String[] args) throws Exception {
    FileWriter out = new FileWriter("temp/test2.txt");

    char[] chars = new char[] {'A','B','C','가','각','간','똘','똥'}; // 문자 배열은 char에 담는다.

    out.write(chars); // 문자 배열 전체를 출력한다.
    // 당연히 UCS2를 JVM 기본 문자 코드표에 따라 변환하여 출력한다.
    // JVM이 입출력 문자 코드표로 UTF-8을 사용한다면
    // 영어는 1바이트로 변환되어 출력될 것이고,
    // 한글은 3바이트로 변환되어 출력될 것이다.
    //    JVM(UCS2)     File(UTF-8)
    // A  00 41     ==> 41
    // B  00 42     ==> 42
    // C  00 43     ==> 43
    // 가 ac 00     ==> ea b0 80
    // 각 ac 01     ==> ea b0 81
    // 간 ac 04     ==> ea b0 84
    // 똘 b6 18     ==> eb 98 98
    // 똥 b6 25     ==> eb 98 a5

    out.close();

    System.out.println("데이터 출력 완료!");
  }
}