package com.eomcs.design_pattern.observer.after.c;

public class Test01 {
  public static void main(String[] args) {
    Car car = new Car();

    car.addCarObserver(new SafeBeltCarObserver());

    // 엔진 오일을 검사할 옵저버를 등록한다.
    car.addCarObserver(new EngineOilCarObserver());

    car.start();

    car.run();

    car.stop();

    // 업그레이드를 수행한 다음 시간이 지난 후
    // 2) 자동차 시동 걸 때 엔진 오일 검사 기능을 추가한다.
    // => 자동차의 시동이 걸릴 때, 보고를 받을 객체 BreakOilCarObserver를 준비한다.
    //    그 객체에서 엔진 오일을 검사한다.
    // => 시동 걸 때 수행할 기능을 carStarted() 메서드에 정의한다.
    // => Car 객체에 옵저버를 등록한다.
  }
}