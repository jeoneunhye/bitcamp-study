// 상속 - Generalization 수행 후
package com.eomcs.oop.ex08.b;

// Sedan과 Truck의 공통 기능인 
// start(), shutdown(), run() 메서드를 추출하여 
// Car 클래스를 만들고 
// Sedan과 Truck은 Car 클래스의 서브 클래스가 된다.

// start()와 shutdown()은 
// Sedan이나 Truck 모두 같은 작업을 수행하기 때문에 상속받은 것을 그대로 사용하면 되지만,
// run()은 Sedan과 Truck이 서로 다르게 작업하기 때문에 오버라이딩해야 한다.
public class Exam01 {
  public static void main(String[] args) {
    Sedan s = new Sedan();
    Truck t = new Truck();
    Car c = new Car();  // 수퍼 클래스의 인스턴스 생성이 가능하다 -> 부적절
  }
}