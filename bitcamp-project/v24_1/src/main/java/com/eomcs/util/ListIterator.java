package com.eomcs.util;
// List 객체에서 Iterator 규칙에 따라 값을 꺼내주는 클래스를 정의
public class ListIterator<E> implements Iterator<E> {
  List<E> list;
  int cursor;

  public ListIterator(List<E> list) {
    this.list = list;   // 생성자로 List를 파라미터로 받아 놓음
  }

  @Override
  public boolean hasNext() {
    return cursor < this.list.size(); // 가리키는 커서가 꺼낼 게 있다면 true!
  }

  @Override
  public E next() {
    return list.get(cursor++); // 커서가 가리키는 값을 꺼내고 그 다음, 커서를 증가시키자
  }
}