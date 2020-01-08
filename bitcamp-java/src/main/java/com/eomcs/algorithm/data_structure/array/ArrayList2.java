package com.eomcs.algorithm.data_structure.array;

import java.util.Arrays;

public class ArrayList2<E> {
  private static final int DEFAULT_CAPACITY = 100;
  Object[] elementData;
  int size;

  public ArrayList2() {
    this.elementData = new Object[DEFAULT_CAPACITY];
  }

  public ArrayList2(int initialCapacity) {
    if (initialCapacity < DEFAULT_CAPACITY)
      this.elementData = new Object[DEFAULT_CAPACITY];
    else
      this.elementData = new Object[initialCapacity];
  }
  
  public void add(E e) { // 객체를 목록에 저장한다.
    this.elementData[this.size] = e;
    this.size++;
  }
  
  @SuppressWarnings("unchecked")
  public E get(int index) { // 객체를 목록에서 꺼낸다.
    return (E) this.elementData[index];
  }
  
  public void set(int index, E e) {
    this.elementData[index] = e;
  }
  
  public E remove(int index) {
    E oldValue = (E) this.elementData[index];
    System.arraycopy(this.elementData, index + 1,
        this.elementData, index , this.size - (index + 1));
    /*
    for (int i = index + 1; i < this.size; i++) {
      this.elementData[i - 1] = this.elementData[i];
    }
    this.size--;
    */
    return (E) oldValue;
  }
  
  public int size() {
    return this.size;
  }
  
  public Object[] toArray() {
    /*
    Object[] arr = new Object[this.size];
    for (int i = 0; i < this.size; i++) {
      arr[i] = this.elementData[i];
    }
    return arr;
    */
    return Arrays.copyOf(this.elementData, this.size);
  }
}