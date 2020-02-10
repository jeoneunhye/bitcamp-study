package com.eomcs.util;

public abstract class AbstractList<E> implements List<E> {
  protected int size;

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator<E> iterator() {
    // ListIterator 클래스는 iterator() 메서드 안에서만 사용되는 중첩 클래스다.
    // 멤버 클래스 ListIterator를 Iterator()의 로컬 클래스로 위치 이동
    class ListIterator<T> implements Iterator<T> {
      List<T> list;
      int cursor;

      @SuppressWarnings("unchecked")
      public ListIterator() {
        this.list = (List<T>) AbstractList.this;
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

    return /*this*/ new ListIterator<E>();
    // ListIterator는 더이상 AbstractList의 멤버 클래스가 아니기 때문에
    // 인스턴스를 생성할 때 바깥 클래스의 인스턴스 주소를 주면 안 된다.
    // 즉 생성자를 호출하는 앞쪽에 this를 붙여서는 안 된다.
  }
}