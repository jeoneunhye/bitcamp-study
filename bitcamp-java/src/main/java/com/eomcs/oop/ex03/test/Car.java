package com.eomcs.oop.ex03.test;
// +Test03.java
public class Car {
  boolean on;   // 여러 대의 자동차 시동 on/off 정보를 저장하기 위해 인스턴스 필드로 설정
  int speed;
  int distance; // 총 주행거리
  String model; // 차종
  String maker; // 제조사 현대? 기아?

  Engine engine;

  Car() {
    this.engine = new Engine(4, 16, 1980);  // 4기통 16밸브 1980cc 엔진의 값을 가졌다.
  }
  
  // 유지보수하기 좋도록 CarHandler가 아닌 자동차 정보가 담겨 있는 Car 클래스에 메서드를 넣는다.
  // Car 설계도에 따라서 만든 메모리를 다루는 메서드 -> Car 클래스에서 non-static 메서드로 만들 수 있고, 직관적 보기가 가능해진다.
  /*static*/ void start(/*Car c*/) {    // Car c: Car 레퍼런스의 주소를 파라미터로 받아 가져오겠다
    /*c*/this.on = true;
  }

  /*static*/ void stop(/*Car c*/) {
    /*c*/this.on = false;
  }

  void check() { // 자동차 정비
    if (this.engine.oilState == 0) {    // 여기서 this는 car다
      this.engine.cleanOil();   // Engine 클래스에 있는 연산자를 사용하기만 한 것이기 때문에 Car 클래스에 두어도 괜찮다.
      // 자동차의 엔진 오일량이 0이면 엔진 오일을 10으로 채워라
      System.out.println("자동차의 엔진 오일을 교환했습니다!");
    }
    System.out.println("자동차를 정비했습니다!");
  } // Car 클래스 생성자에서 engine의 정보를 준비시켜야만 실행이 가능하다.
  // (인스턴스가 제대로 쓰일 수 있도록 유효한 값으로 초기화하는 생성자의 역할)
  
  void cleanOil() {
    this.engine.oilState = 10;
  }
  // cleanOil- 부적절한 메서드의 위치
  // Engine의 oilState 정보를 Car 클래스에서 조회할 수는 있지만 값을 다루어서는 안 된다(refactoring 대상)
}