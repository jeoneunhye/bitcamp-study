package com.eomcs.util;

import java.sql.Date;
import java.util.Scanner;

public class Prompt {
  // 스캐너 객체 필요
  Scanner input;
  
  // 생성자를 통해 스캐너 객체 초기화
  public Prompt(Scanner input) {
    this.input = input;
  }
  
  // private->public
  public String inputString(String label) {
    System.out.print(label);
    return input.nextLine();
  }

  // inputString 오버로딩
  public String inputString(String label, String defaultValue) {
    System.out.print(label);
    String value = input.nextLine();
    if (value.length() == 0) { // 문자열이 없으면
      return defaultValue; // 파라미터로 준 값
    }
    return value; // 사용자가 입력한 값
  }

  public int inputInt(String label) {
    System.out.print(label);
    return Integer.parseInt(input.nextLine()); // 입력값을 정수로 변환
  }

  // inputInt 오버로딩
  public int inputInt(String label, int defaultValue) {
    System.out.print(label);
    String value = input.nextLine();
    if (value.length() == 0) {
      return defaultValue;
    }
    return Integer.parseInt(value);
  }

  public Date inputDate(String label) {
    System.out.print(label);
    return Date.valueOf(input.nextLine());
  }

  // inputDate 오버로딩
  public Date inputDate(String label, Date defaultValue) {
    System.out.print(label);
    String value = input.nextLine();
    if (value.length() == 0) {
      return defaultValue;
    }
    return Date.valueOf(value);
  }
}