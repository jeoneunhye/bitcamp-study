package com.eomcs.oop.ex02.study;
// +Car.java
public class Test03 {
  public static void main(String[] args) {
    // Car 클래스의 스태틱 붙은 변수는 별도 메모리를 만들 필요없이 바로 쓸 수 있다.
    // 자동차 '한 대'의 정보만을 저장할 수 있다.
    // 스태틱은 클래스당 한 번만 로딩된다.
    // Car.name = "봉봉";
    // Car. ...
    Car c1 = new Car();
    Car c2 = new Car();
    Car c3 = new Car();
    // 스태틱 필드를 인스턴스 필드로 바꾸고 저장소를 만들었다.
    // 이제 자동차 여러 대의 정보를 저장할 수 있다.

    // 저장소를 만들었으면 레퍼런스.변수명으로 차의 정보를 불러온다
    c1.name = "봉봉";
    c1.type = 1;
    c1.color = "빨강";
    c1.speed = 0;
    c1.energy = 100;
    c1.power = 200;

    c2.name = "호호";
    c2.type = 2;
    c2.color = "검정";
    c2.speed = 0;
    c2.energy = 100;
    c2.power = 300;

    // 아래는 연산자 메서드에 static을 빼기 전의 식이다 클래스명.메서드명(피연산자);
    //  Car.speedUp(c1);    // 0+10
    //  Car.speedUp(c1);    // 10+10
    //  Car.speedUp(c2);    // 0+10
    //  System.out.printf("c1의 속도: %d\n", c1.speed);    // 20
    //  System.out.printf("c2의 속도: %d\n", c2.speed);    // 10

    // 인스턴스 메서드로 만들고 나서의 식이다 레퍼런스(인스턴스주소).메서드명();
    c1.speedUp();
    c1.speedUp();
    c2.speedUp();
    System.out.printf("c1의 속도: %d\n", c1.speed);
    System.out.printf("c2의 속도: %d\n", c2.speed);

    System.out.printf("c1 vs c2의 힘: %d\n",
        // 연산자 메서드에 static을 빼기 전의 식
        // Car.comparePower(c1, c2));  // 1 : c1이 더 크다
        c1.comparePower(c2)); // 동일한 값을 가지나 위보다 직관적. c1과 c2를 비교
  }
}