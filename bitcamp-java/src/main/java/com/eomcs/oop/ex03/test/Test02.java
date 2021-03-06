package com.eomcs.oop.ex03.test;
// ex03-Exam0612 참고
public class Test02 {
  Test02() {
    // 생성자가 기본으로 하는 일
    // this에 넘어온 객체의 주소를 저장하고 -> super(); 슈퍼클래스 호출
    // 필드 초기화하면 생성자에도 x = 100;을 수행한다!
    // 자바 컴파일러가 인스턴스 초기화 블록과 필드 초기화한 것을
    // 생성자의 처음 부분에 카피한다는 것이 증명됨 
    // 밖에서 필드 선언만 하고 생성자 안에서 x = 100;을 하는 것과 똑같은 결과가 나온다.
    // this.x = 100;
    // this.x = 200;
    this.x = 300;   // 최종 x는 300
  }
  
  Test02(int a) {
    // this.x = 100;
    // this.x = 200;
  }
  
  int x = 100;
  
  {
    this.x = 200;
  }
}
