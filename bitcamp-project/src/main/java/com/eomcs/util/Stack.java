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
    return new StackIterator<E>(this);
    // static 클래스인 StackIterator는 내장 변수 this를 가지고 있지 않다.
    // 그래서 호출하는 쪽에서 파라미터로 Stack 구현체를 넘겨줘야 한다.
  }

  // StackIterator 클래스는 Stack 클래스의 메서드에서만 필요로 하는 클래스
  // => 이동하여 static nested 클래스로 변경했다.
  // new Stack(); 하면 중첩 클래스인 StackIterator 객체는 생성되지 않는다.
  /*public*/static class StackIterator<E> implements Iterator<E> {
    Stack<E> stack;

    public StackIterator(Stack<E> stack) {
      this.stack = stack.clone();
    }

    @Override
    public boolean hasNext() {
      return !stack.empty();
    }

    @Override
    public E next() {
      return stack.pop();
    }
  }
}