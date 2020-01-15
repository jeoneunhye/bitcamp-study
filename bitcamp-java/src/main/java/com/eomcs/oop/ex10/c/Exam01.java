// 템플릿 메서드를 구현한 클래스 사용
package com.eomcs.oop.ex10.c;

public class Exam01 {
  public static void main(String[] args) {
    Restaurant r = new Restaurant();
    Farm f = new Farm();

    work(r); // Building을 상속받은 레스토랑 객체를 파라미터로 받음
    System.out.println("---------------------");
    work(f); // Building을 상속받은 농장 객체를 파라미터로 받음
  }
  
  static void work(Building obj) {
    // 레스토랑이나 농장 건축의 기본 흐름은 이미 수퍼 클래스에 정의되어 있다. (Building)
    // 착수와 완료는 서브 클래스가 오버라이딩했기 때문에 각자 다르다. (Farm, Restaurant)
    obj.build();
  }
}