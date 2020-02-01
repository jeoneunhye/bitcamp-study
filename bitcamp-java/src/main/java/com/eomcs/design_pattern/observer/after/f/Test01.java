package com.eomcs.design_pattern.observer.after.f;

public class Test01 {
  public static void main(String[] args) {
    Car car = new Car();

    car.addCarObserver(new SafeBeltCarObserver());

    car.addCarObserver(new EngineOilCarObserver());

    car.addCarObserver(new BreakOilCarObserver());

    car.addCarObserver(new LightOffCarObserver());

    // 자동차 썬루프를 닫는 옵저버를 등록한다.
    car.addCarObserver(new SunRoofCloseCarObserver());

    car.start();

    car.run();

    car.stop();

    // 업그레이드를 수행한 다음 시간이 지난 후
    // 5) 자동차의 시동을 끌 때 썬루프를 자동으로 닫는 기능을 추가한다.
    // => 자동차의 시동이 꺼질 때, 보고를 받을 객체 SunRoofCloseCarObserver를 준비한다.
    //    그 객체에서 썬루프를 닫는다.
    // => Car 객체에 옵저버를 등록한다.
  }
}