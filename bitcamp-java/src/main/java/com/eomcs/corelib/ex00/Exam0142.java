// String - 다형적 변수와 형변환, toString()
package com.eomcs.corelib.ex01;

public class Exam0142 {
  public static void main(String[] args) {
    Object obj = new String("Hello"); // 인스턴스 주소가 100이라 가정하자

    // obj를 통해 원래 인스턴스 메서드를 호출하고 싶다면
    // 다음과 같이 원래 타입으로 형변환하라
    String str = ((String) obj).toLowerCase(); // 괄호 주의!!순서 바뀜
    System.out.println(str); // hello
    // 형변환하여 String 클래스의 메서드를 실행할 수 있게 된다.
    
    // 또는 다음과 같이 원래 타입의 레퍼런스에 저장한 다음 사용하라.
    String x1 = (String) obj; // x1의 주소는 100
    str = x1.toLowerCase();
    System.out.println(str); // Hello
  }
}