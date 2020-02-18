package com.eomcs.util;

import java.util.Arrays;

public class ArrayList<E> extends List<E> { // List 상속받음
  private static final int DEFAULT_CAPACITY = 15;
  Object[] elementData;
  // int size;

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
  
  @Override // 수퍼 클래스의 메서드 재정의
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
  
  /*
  public int size() {
    return this.size;
  }
  */
  
  @Override
  public Object[] toArray() {
    return Arrays.copyOf(this.elementData, this.size);
  }
  
  @Override
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
}