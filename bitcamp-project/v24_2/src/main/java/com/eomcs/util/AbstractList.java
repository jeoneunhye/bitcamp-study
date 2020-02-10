package com.eomcs.util;

public abstract class AbstractList<E> implements List<E> {
  protected int size;

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator<E> iterator() {
    return new ListIterator<E>(this);
    // static 클래스인 ListIterator는 내장 변수 this를 가지고 있지 않다.
    // 그래서 호출하는 쪽에서 파라미터로 List 구현체를 넘겨줘야 한다.
  }

  // ListIterator 클래스는 AbstractList 클래스의 메서드에서만 필요로 하는 클래스
  // => 이동하여 static nested 클래스로 변경했다.
  // new AbstractList(); 하면 중첩 클래스인 ListIterator 객체는 생성되지 않는다.
  /*public*/static class ListIterator<E> implements Iterator<E> {
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