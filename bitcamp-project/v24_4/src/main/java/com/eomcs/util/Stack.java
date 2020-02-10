package com.eomcs.util;

import java.util.Arrays;

public class Stack<E> implements Cloneable {
  private static final int DEFAULT_CAPACITY = 10;

  Object[] elementData;
  int size;

  public Stack() {
    this.elementData = new Object[DEFAULT_CAPACITY];
    this.size = 0;
  }

  public void push(E value) {
    if (this.size == this.elementData.length) {
      grow();
    }

    this.elementData[size++] = value;
  }

  private void grow() {
    this.elementData = Arrays.copyOf(this.elementData, newCapacity());
  }

  private int newCapacity() {
    int oldCapacity = this.elementData.length;
    return oldCapacity + (oldCapacity >> 1);
  }

  @SuppressWarnings("unchecked")
  public E pop() {
    if (this.empty())
      return null;

    E value = (E) this.elementData[--this.size];
    this.elementData[this.size] = null;

    return value;
  }

  public boolean empty() {
    return this.size == 0;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Stack<E> clone() {
    try {
      Stack<E> temp = (Stack<E>) super.clone();

      Object[] arr = new Object[this.size];

      for (int i = 0; i < this.size; i++) {
        arr[i] = this.elementData[i];
      }

      temp.elementData = arr;

      return temp;
    } catch (CloneNotSupportedException ex) {
      System.out.println(ex);
      return null;
    }
  }

  public Iterator<E> iterator() {
    // StackIterator 클래스는 iterator() 메서드 안에서만 사용되는 중첩 클래스다.
    // 멤버 클래스 StackIterator를 Iterator()의 로컬 클래스로 위치 이동
    class StackIterator<T> implements Iterator<T> {
      Stack<T> stack;

      @SuppressWarnings("unchecked")
      public StackIterator() {
        this.stack = (Stack<T>) Stack.this.clone();
      }

      @Override
      public boolean hasNext() {
        return !stack.empty();
      }

      @Override
      public T next() {
        return stack.pop();
      }
    }

    return /*this.*/new StackIterator<E>();
    // StackIterator는 더이상 Stack의 멤버 클래스가 아니기 때문에
    // 인스턴스를 생성할 때 바깥 클래스의 인스턴스 주소를 주면 안 된다.
    // 즉 생성자를 호출하는 앞쪽에 this를 붙여서는 안 된다.
  }
}