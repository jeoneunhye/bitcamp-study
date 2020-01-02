package com.eomcs.oop.ex05.test;

public class Sedan extends Car {    // Car는 부모, 상위, super 클래스 Sedan은 Child, 하위, sub 클래스
  public String model;
  public String maker;
  public int capacity;

  public boolean sunroof;
  public boolean auto;
  
  public Sedan() {}

  public Sedan(String model, String maker, int capacity,
      boolean sunroof, boolean auto) {
  // this.model = model;
  // this.maker = maker;
  // this.capacity = capacity;
    super(model, maker, capacity);
    
    this.sunroof = sunroof;
    this.auto = auto;
  }
}