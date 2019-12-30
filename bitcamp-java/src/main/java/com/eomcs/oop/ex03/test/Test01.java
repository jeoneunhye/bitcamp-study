package com.eomcs.oop.ex03.test;
import com.eomcs.oop.ex03.test.sub.A;

class Score {   // public하려면 Score 단독 클래스로 분리해야 한다. 프로그램이 그렇게 정해 놓음
  static int count;    // 모든 학생이 공유. 인스턴스를 계속해서 만들 필요가 없다.
  // Method Area에서 Score 클래스가 올라올 때 count라는 변수가 한 번 생성된다.
  // JVM이 종료될 때까지 유지된다. heap은 인스턴스 주소를 잃어버릴 경우 garbage collector가 지울 가능성
  String name;
  int kor;
  int eng;
  int math;
  int sum;
  float aver;
  // 이름과 점수를 각각 저장, 개별 관리가 필요-> 인스턴스 메서드

  /*default 생성자*/ Score() { // 생성자는 void를 붙이지 마라. 일반 메서드가 돼버린다.
    System.out.println("Score() - 1");
    Score.increase();
  }
  // ★생성자의 존재 이유? 인스턴스를 생성하고 무조건 호출되기 때문에, 제대로 사용할 수 있도록 미리 유효한 값으로 설정(초기화)해주는 것
  // 자바의 모든 클래스는 '한 개 이상'의 생성자가 존재한다.

  Score(String name, int kor, int eng, int math) { // 생성자는 void를 붙이지 마라. 일반 메서드가 돼버린다.
    System.out.println("Score() - 2");
    this.name  = name;
    this.kor = kor;
    this.eng = eng;
    this.math = math;
    this.compute();
    Score.increase();
    // 생성자 단계에서 값을 받아 집어넣고 계산까지 끝냄 -> 간편

    // 둘 중 어느 생성자가 실행되는지 어떻게 아냐 - 파라미터에 아무것도 안주면 기본 생성자를 호출하고 값을 주면 아래 생성자를 호출
    // 기본 생성자가 없고 아래 생성자만 있으면 무조건 파라미터에 값을 줘야 한다.
  }

  //  static void compute(Score s) {    // 매번 동일한 코드를 작성할 필요가 없다. Score.compute(s1);하면 끝
  //    s.sum = s.kor + s.eng + s.math;
  //    s.aver = s.sum / 3f;
  //  }
  void compute() {   // 더 좋은 방법. 파라미터를 받을 필요가 없다 s1.compute2(); 직관적 보기 가능
    this.sum = this.kor + this.eng + this.math;
    this.aver = this.sum / 3f;
  }

  // ★연산자 메서드를 스태틱 메서드? 클래스 메서드? 무엇으로 만들지 결정하는 방법
  // 인스턴스 변수를 사용하지 않는 경우 static 연산자 메서드로 만들어라
  static void increase() {
    Score.count++;
  }
}

public class Test01 {
  public static void main(String[] args) {    //main 메서드가 있어야만 run이 가능해짐
    //com.eomcs.oop.ex03.test.sub.A obj;    // import 전
    A.v1 = 100; // static 변수 바로 사용 가능
    A obj;  // A가 public이어야만 test 패키지에서 사용 가능하다. import 후

    Score s1 = new Score("홍길동", 100, 90, 80);
    System.out.println("--------");
    // s1: stack 영역에 생성되는 로컬 변수. 메서드 호출이 끝나면 사라짐
    // new Score();: 인스턴스는 heap에 저장
    //    Score.count = 1;
    s1.name = "홍길동";
    s1.kor = 100;
    s1.eng = 90;
    s1.math = 80;
    //    s1.sum = s1.kor + s1.eng + s1.math;
    //    s1.aver = s1.sum / 3f;    // float 3과 연산을 하기 위해 sum에 암시적 형변환이 이루어짐
    //    Score.compute(s1);
    s1.compute(); // s1 객체에 대해 compute라는 연산자를 실행한다.
    Score.increase();

    Score s2 = new Score("임꺽정", 90, 80, 70);
    System.out.println("--------");
    //    Score.count = 2;
    //    s2.name = "임꺽정";
    //    s2.kor = 90;
    //    s2.eng = 80;
    //    s2.math = 70;
    //    s2.sum = s2.kor + s2.eng + s2.math;
    //    s2.aver = s2.sum / 3f;
    //    Score.compute(s2);
    s2.compute();
    Score.increase();
  }
}