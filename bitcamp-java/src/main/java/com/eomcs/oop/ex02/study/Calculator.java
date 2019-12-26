package com.eomcs.oop.ex02.study;

public class Calculator {
  // 계산 결과를 개별적으로 관리하고 싶다면 '인스턴스 필드'로 선언해야 한다.(static 제거)
  /*static*/ int result;    // default값은 0
  // 메서드가 작업한 결과를 보관하기 위해 필드 문법을 도입

  // static int plus(int p1, p2) {
  // return p1 + p2;
  // }

  // 개별적으로 관리되는 인스턴스 변수 result를 사용하고 싶다면,
  // 파라미터로 인스턴스의 주소를 받아야 한다.
  /*static*/void plus(/*Calculator that, */int value) {    // that이 가리키는 인스턴스에 있는 result
    // 특정 목적을 위해 확보된(찜해놓은) 키워드 reserved: this, int 등등 변수로 쓸 수 없다
    // 내부적으로 보관=내부 변수에 저장하기 때문에 return할 필요없다
    //    that.result += value;    // result = result + value
    this.result += value;
  }
  /*static*/void minus(/*Calculator that, */int value) {
    this.result -= value;    // result = result - value
  }
  /*static*/void multiple(/*Calculator that, */int value) {
    this.result *= value;
  }
  /*static*/void divide(/*Calculator that, */int value) {
    this.result /= value; // 소수점 이하가 잘리는 나누기
  }
}