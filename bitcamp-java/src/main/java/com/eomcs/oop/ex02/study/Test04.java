package com.eomcs.oop.ex02.study;
// + GasStation.java
public class Test04 {
  public static void main(String[] args) {
    //    //조회 용도는 final 설정 -> 자동차 관련 정보이므로 Car 클래스로 이동
    //    final int OIL = 1;
    //    final int GAS = 2;

    // Car 클래스의 스태틱 붙은 변수는 별도 메모리를 만들 필요 없이 바로 쓸 수 있다.
    // 자동차 '한 대'의 정보만을 저장할 수 있다.
    // 스태틱은 클래스당 한 번만 로딩된다.
    // Car.name = "봉봉";
    // Car. ...

    Car c1 = new Car();
    Car c2 = new Car();
    Car c3 = new Car();
    // 인스턴스 필드로 만들고 저장소를 만들었다.
    // 이제 자동차 여러 대의 정보를 저장할 수 있다.

    // 저장소를 만들었으면 레퍼런스.변수명으로 차의 정보를 불러온다
    c1.name = "봉봉";
    c1.type = c1.OIL;
    c1.color = "빨강";
    c1.speed = 0;
    c1.energy = 80;
    c1.power = 200;

    c2.name = "호호";
    c2.type = c2.GAS;
    c2.color = "검정";
    c2.speed = 0;
    c2.energy = 60;
    c2.power = 300;

    // refuel(주유) 전 출력
    System.out.printf("c1의 잔류량 : %d\n", c1.energy);
    System.out.printf("c2의 잔류량 : %d\n", c2.energy);
    // GasStation type 변수와 refuel 메서드에 static을 빼기 전
    //    GasStation.type = 1;    // Oil 주유소
    //    GasStation.refuel(c1);  // 충전 가능
    //    GasStation.refuel(c2);  // 충전 불가

    // 인스턴스 필드와 인스턴스 메서드로 바꾸고 난 후의 식
    GasStation station1 = new GasStation();
    station1.type = c1.OIL;
    station1.name = "강남주유소";

    GasStation station2 = new GasStation();
    station2.type = c2.GAS;
    station2.name = "서초주유소";
 
    station1.refuel(c1);
    station2.refuel(c2);

    System.out.printf("c1의 잔류량 : %d\n", c1.energy);
    System.out.printf("c2의 잔류량 : %d\n", c2.energy);

    // 클래스 메서드는 클래스 이름으로 호출하자
    GasStation.clean(c1);
    GasStation.clean(c2);
  }
}