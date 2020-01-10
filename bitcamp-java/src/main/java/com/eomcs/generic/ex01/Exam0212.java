// 제네릭(Generic) 문법 정리- 레퍼런스와 인스턴스 생성
package com.eomcs.generic.ex01;

import java.util.ArrayList;
import java.util.HashSet;

public class Exam0212 {
  public static void main(String[] args) {
    ArrayList<Object> list1;
    // 제네릭 타입을 Object로 지정하면
    // 그렇게 지정된 ArrayList 객체만 list2에 저장할 수 있다.
    list1 = new ArrayList();
    list1 = new ArrayList<>();
    list1 = new ArrayList<Object>();
    //list1 = new ArrayList<String>();
    //list1 = new ArrayList<Member>();
    
    // ArrayList가 다루는 타입에 상관없이 ArrayList 레퍼런스를 선언하고 싶다면
    // list1처럼 선언하지 말고, 다음과 같이 <?>를 붙여라!
  }
}