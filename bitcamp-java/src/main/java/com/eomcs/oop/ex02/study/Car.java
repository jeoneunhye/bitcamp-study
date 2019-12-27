package com.eomcs.oop.ex02.study;
// +Test03.java
public class Car {
  public static final int OIL = 1;
  public static final int GAS = 2;
  // Car라는 클래스가 method area에 로딩될 때 미리 만들어진다.
  // 모든 인스턴스가 똑같은 값을 가지는 경우 다 static을 붙이자 -> heap에 두면 안된다.
  // ex03의 Exam0160 참고

  String name;
  int type;
  String color;
  int speed;
  int energy;
  int power;
  int cleanLevel;
  // 변수에 static을 붙이면 클래스가 로딩될 때 method area에 자동으로 만들어짐
  // static을 떼고 인스턴스 필드가 됐다. new 명령을 해야만 heap에 만들어진다.
  // Test03 클래스에서 Car 클래스 이름으로 접근 불가능 -> new로 저장소를 만들어야 함

  // 데이터들을 다루는 연산자로서의 역할을 수행하는 메서드 ~> static을 떼면 직관적 보기 가능
  /*static*/void speedUp(/*Car c*/) {   //해당 자동차의 speed를 10만큼 올린다.
    // 클래스 메서드는 파라미터로 자동차 정보가 저장된 인스턴스 주소를 받는다.
    // 인스턴스 메서드로 변경 -> Car this = 메서드를 호출할 때 넘겨준 인스턴스 주소;
    if (this.speed >= 100)
      return;   // 현재 상태에서 이 메서드를 끝내겠다.
    this.speed += 10;
  }

  int comparePower(Car c/*Car c1, Car c2*/) {
    // 비교했으니 결과가 있어야 하므로 int값을 return
    if (this/*c1*/.power > c/*c2*/.power)
      return -1;
    else if (this/*c1*/.power == c/*c2*/.power)
      return 0;
    else
      return 1;
  }
}