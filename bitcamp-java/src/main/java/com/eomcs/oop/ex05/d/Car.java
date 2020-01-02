package com.eomcs.oop.ex05.d;

public class Car {
    public String model; // 차종
    public String maker; // 제조사
    public int capacity; // 수용 인원
    
    public Car() {}
    
    public Car(String model, String maker, int capacity) {
        this.model = model;
        this.maker = maker;
        this.capacity = capacity;
    }
}


