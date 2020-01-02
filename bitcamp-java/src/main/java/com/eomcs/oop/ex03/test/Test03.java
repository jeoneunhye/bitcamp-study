package com.eomcs.oop.ex03.test;
// +Car.java
public class Test03 {
  public static void main(String[] args) {
    Car c1 = new Car(); // Car 설계도에 따라 c1 레퍼런스를 생성한다.
    // c1의 각각의 ★'인스턴스 필드'는 Heap에 저장되고 c1의 인스턴스 주소(번짓수)는 stack에 저장된다.
    Car c2 = new Car();
    Car c3 = null;
    
    //Car.start(c1);
    //Car.start(c2);
    c2.start(); // 인스턴스 메서드로 바꿨기 때문에 인스턴스 주소가 맨 앞으로 간다.
    //c3.start(); // 컴파일 오류는 x runtime error 정상적인 주소 없이는(null) 메서드를 호출할 수 없다.
    
    System.out.printf("c1.on = %b\n", c1.on); // c1.on = false
    System.out.printf("c2.on = %b\n", c2.on); // c2.on = true
    
    c1.check();
  }
}