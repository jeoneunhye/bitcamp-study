package com.eomcs.oop.ex08.test;

public class J {
  class Student {
    String email;
    String pwd;
    String name;
    String tel;
    int grade;    // 최종학력
    boolean working;  // 구직자/재직자

    void print() {
      System.out.println("학생 정보");
    }
  }

  class Teacher {
    String email;
    String pwd;
    String name;
    String tel;
    int pay;
    String major; // 전공정보

    void print() {
      System.out.println("강사 정보");
    }
  }
}