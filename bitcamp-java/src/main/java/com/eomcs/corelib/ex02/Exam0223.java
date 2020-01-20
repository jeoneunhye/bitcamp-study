// Wrapper 클래스 - auto-boxing/auto-unboxing 응용
package com.eomcs.corelib.ex02;

public class Exam0223 {
  static class Member { // toString 메서드는 한 곳에서만 사용할 목적이므로 static 클래스로 만든다.
    String name;
    String tel;

    @Override
    public String toString() {
      return name + ", " + tel;
    }
  }

  public static void main(String[] args) {
    int i = 100;

    Member member = new Member();

    member.name = "홍길동";
    member.tel = "010-1111-2222";

    String str = new String("Hello");

    // 자바가 wrapper 클래스를 이용하여 auto-boxing, auto-unboxing 기능을 제공함으로써
    // primitive data type 또는 클래스의 인스턴스 구분없이 값을 편리하게 저장할 수 있게 되었다.
    Object obj;
    obj = member; // 다형적 변수 문법에 따라 상위 클래스의 레퍼런스에 하위 객체를 저장
    obj = str;

    obj = i;
    // primitive data 값을 어떻게 넣었을까?
    // => auto-boxing 규칙에 따라 Integer.valueOf(i); 문장으로 변환

    Integer obj2 = (Integer) obj;

    System.out.println(i); // 100
    System.out.println(obj2); // 100
  }
}