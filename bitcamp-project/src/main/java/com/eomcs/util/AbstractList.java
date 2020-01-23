package com.eomcs.util;

public abstract class AbstractList<E> implements List<E> {
  // List 규칙에 따라 동작하는 데 필요한 필드가 있다면
  // 다음과 같이 그 규칙을 따르는 클래스 쪽에 필드를 선언하면 된다.
  protected int size;

  // AbstractList는 List 규칙을 포함한다.
  // List 규칙에 정의된 메서드 중에서 다음과 같이 size()를 구현한다.
  // => 여기서 구현해도 되기 때문에 서브 클래스에게 구현을 맡기지 않고 여기서 처리하는 것이다.
  public int size() {
    return size;
  }
  
  //ctrl+space 리턴값 new ListIterator<E>(this) 입력 후 create Class
  @Override
  public Iterator<E> iterator() {
    // LinkedList에서 값을 꺼내줄 Iterator 객체를 준비하여 리턴한다.
    return new ListIterator<E>(this);
  }
  
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
}