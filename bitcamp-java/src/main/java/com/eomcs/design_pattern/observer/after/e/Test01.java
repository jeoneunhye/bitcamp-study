package com.eomcs.design_pattern.observer.after.e;

public class Test01 {
  public static void main(String[] args) {
    Car car = new Car();

    car.addCarObserver(new SafeBeltCarObserver());

    car.addCarObserver(new EngineOilCarObserver());

    car.addCarObserver(new BreakOilCarObserver());

    // 자동차 전조등 전원을 검사할 옵저버를 등록한다.
    car.addCarObserver(new LightOffCarObserver());

    car.start();

    car.run();

    car.stop();

    // 업그레이드를 수행한 다음 시간이 지난 후
    // 4) 자동차의 시동을 끌 때 자동차 전조등을 자동으로 끄는 기능을 추가한다.
    // => 자동차의 시동이 꺼질 때, 보고를 받을 객체 LightOffCarObserver를 준비한다.
    //    그 객체에서 자동차 전조등 전원을 검사한다.
    // => Car 객체에 옵저버를 등록한다.
  }
}