package com.eomcs.basic.ex03;

// 정수 리터럴(literal) _ 언더바 사용법
//
public class Exam22 {
  public static void main(String[] args) {
    //맨 앞/뒷자리만 빼고 _ 아무 위치에나 넣을 수 있음
    System.out.println(2_3500_0000); // 결과값 235000000
    System.out.println(235_000_000); // 결과값 235000000
    //System.out.println(23_5_00_0_000_); 
    System.out.println(01_44);  // 결과값 100
    System.out.println(0_144);
    //System.out.println(0144_);
    //System.out.println(0b_1100100);
    //System.out.println(0b1100100_); // 주석처리 한 것들 : illegal underscore 오류 발생
    System.out.println(0b1100100);
    System.out.println(0x00_64);
    //System.out.println(0x_0064);
    //System.out.println(0x0064_);
    //System.out.println(_0x0064);
  }
}