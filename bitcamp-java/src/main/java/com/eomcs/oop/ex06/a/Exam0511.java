package com.eomcs.oop.ex06.a;

public class Exam0511 {
  public static void main(String[] args) {
    Vehicle v = new Sedan();
    
    // instanceof 연산자
    // 레퍼런스에 들어있는 주소가 특정 클래스의 인스턴스인지 검사한다.
    // 또는 그 하위 클래스의 인스턴스인지 검사한다.
    
    System.out.println(v instanceof Sedan); // true
    System.out.println(v instanceof Car);   // true
    System.out.println(v instanceof Vehicle);   // true
    System.out.println(v instanceof Object);    // true
    
    System.out.println(v instanceof Truck); // false
    System.out.println(v instanceof Bike); // false
  }
}