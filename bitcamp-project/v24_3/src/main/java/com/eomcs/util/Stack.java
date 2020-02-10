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
    return this.new StackIterator<E>(/*this*/);
    // this : non-static 클래스 Stack의 인스턴스 주소로
    // 논스태틱 중첩 클래스(이너 클래스)인 StackIterator에 접근
  }

  // StackIterator non-static nested 클래스로 변경
  /*static*/ class StackIterator<T> implements Iterator<T> {
    Stack<T> stack;

    @SuppressWarnings("unchecked")
    public StackIterator(/*Stack<E> stack*/) {
      this.stack = (Stack<T>) Stack.this.clone/*stack.clone*/();
      // 바깥 클래스인 Stack의 인스턴스 주소 this
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
}