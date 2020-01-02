package com.eomcs.oop.ex05.test;

public class Car {
  public String model; // 차종
  public String maker; // 제조사
  public int capacity; // 수용 인원
  
//  public boolean sunroof;   // 기존 코드에 필드 추가
//  public boolean auto;

  public Car() {}

  public Car(String model, String maker, int capacity) {
    this.model = model;
    this.maker = maker;
    this.capacity = capacity;
  }
  
//  public Car(String model, String maker, int capacity,
//      boolean sunroof, boolean auto) {
//
//    this(model, maker, capacity);
//
//    this.sunroof = sunroof;
//    this.auto = auto;
//  }
}