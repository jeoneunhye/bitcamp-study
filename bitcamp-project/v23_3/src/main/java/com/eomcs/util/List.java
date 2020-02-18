package com.eomcs.util;
// 목록 객체의 사용 규칙을 따로 정의: interface
// => 문법
//    interface 규칙명 {...}
public interface List<E> {
  // 사용 규칙(호출할 메서드 시그니처 형식)이기 때문에 모든 메서드는 추상 메서드다.
  // 또한 규칙은 공개되어야 하기 때문에 모든 메서드는 public이다.
  // => 문법
  //      public abstract 리턴타입 메서드명(파라미터, ...);
  // => public을 생략할 수 있다.
  //      abstract 리턴타입 메서드명(파라미터, ...);
  // => abstract를 생략할 수 있다.
  //      리턴타입 메서드명(파라미터, ...);
  public abstract void add(E e);
  
  /*public*/ abstract void add(int index, E value);
  
  public /*abstract*/ E get(int index);
  
  /*public abstract*/ E set(int index, E e);
  
  E remove(int index);
  
  Object[] toArray();
  
  E[] toArray(E[] arr);
  
  int size(); // 모든 리스트에 size가 필요하기 때문에 size도 정의
}