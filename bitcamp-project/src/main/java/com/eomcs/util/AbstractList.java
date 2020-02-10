package com.eomcs.util;

public abstract class AbstractList<E> implements List<E> {
  protected int size;

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator<E> iterator() {
    return this.new ListIterator<E>(/*this*/);
    // this : non-static 클래스 AbstractList의 인스턴스 주소로
    // 논스태틱 중첩 클래스(이너 클래스)인 ListIterator에 접근
  }

  // ListIterator non-static nested 클래스로 변경
  /*static*/ class ListIterator<T> implements Iterator<T> {
    List<T> list;
    int cursor;

    @SuppressWarnings("unchecked")
    public ListIterator(/*List<E> list*/) {
      this.list = (List<T>) AbstractList.this/*list*/;
      // 바깥 클래스의 this
      // AbstractList의 서브 클래스인 ArrayList, LinkedList의 인스턴스 주소
    }

    @Override
    public boolean hasNext() {
      return cursor < this.list.size();
    }

    @Override
    public T next() {
      return list.get(cursor++);
    }
  }
}