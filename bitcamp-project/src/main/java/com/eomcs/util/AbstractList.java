package com.eomcs.util;

public abstract class AbstractList<E> implements List<E> {
  protected int size;

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator<E> iterator() {
    // LinkedList에서 값을 꺼내줄 Iterator 객체를 준비하여 리턴한다.
    return new ListIterator<E>(this);
  }

  // ListIterator 클래스는 AbstractList 클래스에서만 사용하다.
  // => static nested 클래스로 만들었다.
  // new AbstractList(); 하면 중첩 클래스인 ListIterator 객체는 생성되지 않는다.
  static class ListIterator<E> implements Iterator<E> {
    List<E> list;
    int cursor;

    public ListIterator(List<E> list) {
      this.list = list;
    }

    @Override
    public boolean hasNext() {
      return cursor < this.list.size();
    }

    @Override
    public E next() {
      return list.get(cursor++);
    }
  }
}