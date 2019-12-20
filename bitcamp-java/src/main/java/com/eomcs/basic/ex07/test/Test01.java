package com.eomcs.basic.ex07.test;

public class Test01 {
  public static void main(String[] args) {
    // 입력 값: X, 리턴 값: X의 경우
    m1();    // m이라는 메서드를 실행한다
    //int i = m1();   // 아무것도 리턴받지 않았기 때문에 컴파일 오류 발생
    
    // 입력 값: O, 리턴 값: X의 경우
    m2("홍길동");
    
    // 입력 값: X, 리턴 값: O의 경우
    //m3();   // 리턴 값을 안 받아도 되고 받아도 되는데 받으려면 값을 받는 메모리가 필요
    String s;
    s = m3();
    System.out.println(s);
    
    // 입력 값: O, 리턴 값: O의 경우
    String s2 = m4("홍길동");  //"홍길동" : ★아규먼트
    System.out.println(s2);
  }
  static void m1(/*메서드를 수행하는 데 필요한 값이 있으면 외부로부터 받겠다*/) {
    System.out.println("Hello!");
  }
  static void m2(String name) { // () 안에, 넘겨받을 값의 변수를 적어줘야 한다 String name: ★파라미터
    // 함수 안에 선언된 변수를 "로컬(local) 변수"라 부른다.
    // 로컬 변수 중에서 함수(메서드)가 호출될 때 넘어온 값을
    // 받는 변수를 "파라미터(parameter)"라 부른다.
    // 함수를 호출할 때 넘겨주는 값을 "아규먼트(argument)"라 부른다.
    System.out.println("Hello, " + name);   // 받아서 안에 이용
    }
  static String m3() {    // static 옆에 데이터타입을 따라 적어야 한다. 메서드를 수행한 다음 나온 값을 리턴한다
    String message = "Hello~";
    return message; // 리턴하는 값은 오로지 한 개만 가능하다
  }
  static String m4(String name) {
    //String message = String.format("%s 님 안녕하세요!", name);
    String message = name + "님 안녕하세요!";
    return message;
  }
}
