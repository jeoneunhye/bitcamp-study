package com.eomcs.util;

public interface List<E> {
  void add(E e);

  void add(int index, E value);

  E get(int index);

  E set(int index, E e);

  E remove(int index);

  Object[] toArray();

  E[] toArray(E[] arr);

  int size();

  // E값을 다루는 Iterator를 리턴하는 메서드 선언
  Iterator<E> iterator();
}