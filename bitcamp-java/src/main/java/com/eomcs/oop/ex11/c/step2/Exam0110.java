// 상수를 활용하기 전: 카테고리를 문자열로 받기
package com.eomcs.oop.ex11.c.step2;

public class Exam0110 {
  public static void main(String[] args) {
    // 이전 예제에서는 카테고리를 숫자로 받기 때문에
    // 해당 카테고리가 무엇인지 알 수 없었다.
    // 그래서 따로 주석으로 달았는데 그냥 문자열로 받으면 안 될까?
    Product p = new Product();
    p.category = "appliance/tv";
    p.name = "울트라비전 뷰";
    p.price = 2000000;

    Product p2 = new Product();
    p2.category = "computer/ram";
    p2.name = "삼성모듈램 4GB";
    p2.price = 80000;

    Product p3 = new Product();
    p3.category = "book/novul"; // book/novel의 오타
    p3.name = "토지";
    p3.price = 18000;

    // 제품의 카테고리 값을 문자열로 다루면
    // 따로 주석을 달 필요가 없어 좋다.
    // 단 문자열에 오타가 들어갈 경우가 생기면
    // 프로그램이 의도대로 동작하지 않을 것이다.
  }
}