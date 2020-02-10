package com.eomcs.util;

public abstract class AbstractList<E> implements List<E> {
  protected int size;

  @Override
  public int size() {
    return size;
  }

  // 로컬 클래스 -> 익명 클래스
  // 인스턴스를 한 개만 생성할 거면 익명 클래스로 정의하라.
  // AbstractList.java.01의 리턴 값에 obj 구현체를 바로 집어 넣음
  @Override
  public Iterator<E> iterator() {
    // Iterator<E> obj =
    // 익명 클래스
    // return obj;

    return new Iterator<E>() {
      List<E> list;
      int cursor;

      {
        this.list = AbstractList.this;
      }

      @Override
      public boolean hasNext() {
        return cursor < this.list.size();
      }

      @Override
      public E next() {
        return list.get(cursor++);
      }
    };
  }
}