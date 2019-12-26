package com.eomcs.oop.ex02.study;

public class Score {
  // 변수 선언을 통해 메모리 구조 설계 : 인스턴스 변수
  String name;
  int kor;
  int eng;
  int math;
  int sum;
  float aver;

  // 메서드를 통해 값을 계산하는 기능을 추가: 연산자 정의
  // => 합계와 평균을 계산하는 기능을 추가한다.
  // Score 클래스에 들어간 데이터 값을 다루는 연산자 메서드 : operator
  // 메서드를 다음과 같이 호출할 때
  //  인스턴스주소.메서드명();줏
  // 인스턴스 주소를 자동으로 받는 메서드 문법이 있다.
  //  "인스턴스 메서드"이다.
  // => 인스턴스 메서드는 static을 붙이지 않는다.
  // => 메서드를 호출할 때 넘겨준 인스턴스 주소는 내장 변수 this에 자동 보관된다.
  // => 인스턴스 메서드
  //public static void calculate(Score score) {

  public void calculate(/*메서드 자체 내장 변수 Score this*/) {
    // 인스턴스 메서드는 인스턴스 주소를 받는 내장 변수(built-in)가 있다.
    // 그 변수의 이름은 this이다.
    // 변수를 따로 선언하지 않는다.
    // 데이터를 가공하는 코드를 넣는 기능을 하는 메서드!
    this.sum = this.kor + this.eng + this.math;
    this.aver = this.sum / 3f;
  }
}