package com.eomcs.lms.handler;

import java.util.Arrays;
import com.eomcs.lms.domain.Board;

public class ArrayList {
  private static final int DEFAULT_CAPACITY = 3; 

  private Object[]/*Board[]*/ list;
  // Board 계열의 객체만 저장이 가능하다. -> Object[]로 변경
  private int size;

  public ArrayList() {
    this.list = new Object/*Board*/[DEFAULT_CAPACITY];
  }

  public ArrayList(int capacity) {
    if (capacity > DEFAULT_CAPACITY &&
        capacity < 100000) {
      this.list = new Object/*Board*/[capacity];
    } else {
      this.list = new Object/*Board*/[DEFAULT_CAPACITY];
    }
  }

  public Object[]/*Board[]*/ toArray() {
    return Arrays.copyOf(this.list, this.size);
  }

  public void add(Object obj) {
    if (this.size == this.list.length) {
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      this.list = Arrays.copyOf(this.list, newCapacity);
    }
    this.list[this.size++] = obj;
  }

  public Object/*Board*/ get(int idx/*no*/) {
    // Object에 getNo 메서드가 없기 때문에 게시물 번호가 아닌 인덱스로 찾을 것
    if (idx >= 0 && idx < this.size) {  // 인덱스가 유효한 값이면
      return this.list[idx];
    } else {
      return null;
    }
    // for (int i = 0; i < this.size; i++) {
    //   if (this.list[i].getNo() == no) {
    //     return this.list[i];
    //  }
    // }
    // return null;
  }
}