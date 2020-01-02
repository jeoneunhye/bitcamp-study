package com.eomcs.oop.ex03.test;
//+Car.java
public class Engine {
  int sylinder; // 기통
  int valve;
  int cc;
  int oilState;
  
  // 엔진이 유효하려면 세 개의 필드 값이 필수로 저장되어야 하기 때문에
  // 기본 생성자가 실행되지 않도록 파라미터를 받는 생성자를 만들었다.
  Engine(int sylinder, int valve, int cc) {
    this.sylinder = sylinder;
    this.valve = valve;
    this.cc = cc;
    this.oilState = 0;  // 주석처리해도 int의 기본값이 0이기 때문에 Car 클래스의 check() 메서드가 실행 가능하다.
  } // 생성자를 이용하여 최소의 기본값으로 세팅하도록 강요
  
  void cleanOil() { // 엔진 오일 교체
    this.oilState = 10;
  }
}