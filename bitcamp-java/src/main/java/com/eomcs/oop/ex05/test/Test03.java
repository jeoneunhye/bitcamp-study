package com.eomcs.oop.ex05.test;
// +Sedan.java
// Test02를 사용하다가 sunroof와 auto 필드를 추가하였다.
public class Test03 {
  public static void main(String[] args) {
    // 새 프로젝트는 선루프와 자동 변속 여부를 저장한다.
    // Sedan c1 = new Sedan("티코", "현대자동차", 5, false, true);
    // Car2 클래스로 복사해서 기능을 추가했기 때문에 Test02.java에는 영향을 주지 않는다.
    // 하지만! 이렇게 하면 버그 수정이 어렵다.
    
    // 클래스 이름  Car2 -> Sedan 변경 후 수퍼 클래스 Car 상속받기
    // 기능 추가- 필드 추가 선언하고 생성자 파라미터로 받음
    // Car 클래스에 버그가 발생하더라도 Car 클래스만 바꿔주면 된다.
  }
}