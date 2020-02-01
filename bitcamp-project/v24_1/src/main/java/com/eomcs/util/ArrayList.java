package com.eomcs.util;

import java.util.Arrays;

public class ArrayList<E> extends AbstractList<E> {
  private static final int DEFAULT_CAPACITY = 15;
  Object[] elementData;

  public ArrayList() {
    this.elementData = new Object[DEFAULT_CAPACITY];
  }

  public ArrayList(int initialCapacity) {
    if (initialCapacity < DEFAULT_CAPACITY) {
      this.elementData = new Object[DEFAULT_CAPACITY];
    } else {
      this.elementData = new Object[initialCapacity];
    }
  }
  
  public void add(E e) {
    if (this.size == this.elementData.length) {
      grow();
    }

    this.elementData[this.size++] = e;
  }

  @Override
  @SuppressWarnings("unchecked")
  public E get(int index) {
    if (index < 0 || index >= this.size) {
      return null;
    }
    return (E) this.elementData[index];
  }

  @Override
  @SuppressWarnings("unchecked")
  public E set(int index, E e) {
    if (index < 0 || index >= this.size) {
      return null;
    }
    E oldValue = (E) this.elementData[index];
    this.elementData[index] = e;
    return oldValue;
  }

  @Override
  @SuppressWarnings("unchecked")
  public E remove(int index) {
    if (index < 0 || index >= this.size) {
      return null;
    }

    E oldValue = (E) this.elementData[index];

    System.arraycopy(this.elementData, index + 1,
        this.elementData, index, this.size - (index + 1));
    this.size--;

    return oldValue;
  }
  
  @Override
  public Object[] toArray() {
    return Arrays.copyOf(this.elementData, this.size);
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public E[] toArray(E[] arr) {
    if (arr.length < this.size) {
      return (E[]) Arrays.copyOf(this.elementData, this.size, arr.getClass());
    }
    System.arraycopy(this.elementData,  0,  arr,  0, this.size);
    return arr;
  }
  
  @Override
  public void add(int index, E value) {
    if (index < 0 || index >= this.size)
      return;

    if (this.size == this.elementData.length) {
      grow();
    }

    for (int i = size - 1; i >= index; i--)
      this.elementData[i + 1] = this.elementData[i];

    this.elementData[index] = value;
    this.size++;
  }
  
  private Object[] grow() {
    return this.elementData = Arrays.copyOf(this.elementData, 
        newCapacity());
  }
  
  private int newCapacity() {
    int oldSize = this.elementData.length;
    return oldSize + (oldSize >> 1);
  }
  
  /* ----> AbstractList 클래스로 이동! (LinkedList와 함께 사용할 메서드이므로)
  // ctrl+space
  @Override
  public Iterator<E> iterator() { // Iterator를 리턴하는 iterator 메서드
    /* ----> ArrayListIterator 클래스로 이동!
    // ArrayList 객체에서 Iterator 규칙에 따라 값을 꺼내주는 클래스를 정의
    class IteratorImpl<T> implements Iterator<T> {
      ArrayList<T> list;
      int cursor;
      
      public IteratorImpl(ArrayList<T> list) {
        this.list = list;   // 생성자로 ArrayList를 파라미터로 받아 놓음
      }
      
      @Override
      public boolean hasNext() {
        return cursor < this.list.size(); // 꺼낼 게 있다면 true!
      }
      
      @Override
      public T next() {
        return list.get(cursor++); // 커서가 가리키는 값을 꺼내고 그 다음, 커서를 증가시키자
      }
    }
    // <---여기까지
    
    // ArrayList에서 값을 꺼내주는 Iterator 구현체를 준비하여 리턴한다.
    return new ListIterator<E>(this);
  }
  */
}