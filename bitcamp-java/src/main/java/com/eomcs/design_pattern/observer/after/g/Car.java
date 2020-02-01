package com.eomcs.design_pattern.observer.after.g;

import java.util.ArrayList;
import java.util.List;

public class Car {
  // 관찰자(Observer)의 객체 주소를 보관한다.
  List<CarObserver> observers = new ArrayList<>();

  // 자동차의 상태 변경을 보고받을 관찰자를 등록한다.
  public void addCarObserver(CarObserver observer) {
    observers.add(observer);
  }

  // 자동차의 상태 변경을 보고받는 관찰자를 제거한다.
  public void removeCarObserver(CarObserver observer) {
    observers.remove(observer);
  }

  private void notifyObserverOnStarted() {
    // 자동차의 시동을 걸면 등록된 관찰자들에게 알린다: 메서드의 이름만 보고 기능을 알 수 있다.
    for (CarObserver observer : observers) {
      observer.carStarted(); // 옵저버 인터페이스에서 정의한 carStarted();
    }
  }

  private void notifyObserverOnStopped() {
    // 자동차의 시동을 걸면 등록된 관찰자들에게 알린다: 메서드의 이름만 보고 기능을 알 수 있다.
    for (CarObserver observer : observers) {
      observer.carStopped(); // 옵저버 인터페이스에서 정의한 carStopped();
    }
  }

  public void start() {
    System.out.println("시동을 건다.");

    notifyObserverOnStarted();
  }

  public void run() {
    System.out.println("달린다.");
  }

  public void stop() {
    System.out.println("시동을 끈다.");

    notifyObserverOnStopped();
  }
}