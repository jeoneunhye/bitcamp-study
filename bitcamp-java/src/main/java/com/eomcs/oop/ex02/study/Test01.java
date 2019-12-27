package com.eomcs.oop.ex02.study;

public class Test01 {
  public static void main(String[] args) {
    Score s = new Score();
    // 레퍼런스에 저장된 주소로 찾아가 Score의 설계도에 따라 인스턴스 주소(객체)를 생성
    s.name = "홍길동";
    s.kor = 100;
    s.eng = 90;
    s.math = 80;
    // Score 클래스의 calculate 메서드에 s를 집어넣어 실행해라
    // 이 때 Score 클래스는 public인 상태여야 한다.
    //Score.calculate(s); // 연산자 operator라고 부른다. () 괄호 안이 피연산자
    /*ㄴ>*/s.calculate();  // 앞에 반드시 인스턴스 주소 s를 줘야 한다.
    // 파라미터를 넘겨야하는 번거로움을 줄이기 위해 메서드를 non-static으로 정의
    // s.의 값이 this.로 들어간다. 인스턴스 주소 s가 피연산자가 된다.
    System.out.println(s.sum);
    System.out.println(s.aver);

    //static을 떼면(인스턴스 메서드)
    //Score s2 = new Score(); 새 인스턴스 주소를 생성하고 값을 저장한 뒤
    //s2 값을 이용하여 메서드를 사용하는 것이 가능하다. s2.calculate();
  }
}