package com.eomcs.util;

import java.util.Arrays;

public class ArrayList<E> {
  private static final int DEFAULT_CAPACITY = 15;
  Object[] elementData;
  int size;

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
      int oldSize = this.elementData.length;
      int newSize = oldSize + (oldSize >> 1);

      this.elementData = Arrays.copyOf(this.elementData, newSize);
    }

    this.elementData[this.size++] = e;
  }

  @SuppressWarnings("unchecked")
  public E get(int index) {
    if (index < 0 || index >= this.size) {
      return null;
    }
    return (E) this.elementData[index];
  }

  @SuppressWarnings("unchecked")
  public E set(int index, E e) {
    if (index < 0 || index >= this.size) {
      return null;
    }
    E oldValue = (E) this.elementData[index];
    this.elementData[index] = e;
    return oldValue;
  }

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

  public int size() {
    return this.size;
  }

  public Object[] toArray() {
    return Arrays.copyOf(this.elementData, this.size);
  }
  
  @SuppressWarnings("unchecked")
  public E[] toArray(E[] arr) { // E타입 배열을 파라미터로 받고, E타입 배열을 리턴
    if (arr.length < this.size) {
      // 파라미터로 받은 배열이 작을 때는 새 배열을 만들어 리턴
      return (E[]) Arrays.copyOf(this.elementData, this.size, arr.getClass());
      // arr.getClass() 생성할 배열의 타입을 지정하는 것
    }
    System.arraycopy(this.elementData,  0,  arr,  0, this.size);
    // 원본 배열을, 원본 배열의 0번부터 가져와서, arr 배열에 복사한다, arr 배열의 0번부터 입력, 원본 배열의 this.size개 만큼
    return arr; // 넉넉할 때는 파라미터로 받은 배열을 그대로 리턴
  }
}