package com.eomcs.oop.ex11.c.step3;

public class Product {
  // 카테고리 값을 잘못 입력할 경우를 방지하기 위해
  // 문자열을 상수로 정의한다.
  // 인스턴스마다 따로 다뤄질 게 아니므로 static 정의
  public static final String appliance_tv = "appliance/tv";
  public static final String computer_ram = "computer/ram";
  public static final String book_novel = "book/novel";

  String category;
  String name;
  String maker;
  int price;
}