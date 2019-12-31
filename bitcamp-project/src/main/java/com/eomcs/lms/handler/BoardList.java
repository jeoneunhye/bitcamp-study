package com.eomcs.lms.handler;

import java.util.Arrays;
import com.eomcs.lms.domain.Board;

public class BoardList {
  private static final int DEFAULT_CAPACITY = 3; // BOARD_SIZE

  private Board[] list; // boards/ 배열의 주소를 담음
  private int size; // boardCount/ 자동으로 0으로 초기화! ! !

  public BoardList() {  // 생성자 이용하여 null이었던 배열을 생성해줌
    this.list = new Board[DEFAULT_CAPACITY];
  }

  public BoardList(int capacity) {   // 배열의 크기를 미리 지정
    if (capacity > DEFAULT_CAPACITY &&
        capacity < 100000) {
      this.list = new Board[capacity];  // 원하는 크기만큼
    } else {    // 유효 범위를 벗어나면
      this.list = new Board[DEFAULT_CAPACITY]; // 기본 크기만큼
    }
  }

  public Board[] toArray() {
    /*
    Board[] arr = new Board[this.size];
    // Board 배열을 사이즈 갯수만큼 만들어라
    
    for (int i = 0; i < this.size; i++) {
      arr[i] = this.list[i];
    }
    //  return arr; // this.list가 아님!!!
    // 반복문 돌면서 size만큼 배열 각각의 번짓수를 리턴
    // -----아래의 한 줄로 한 번에 실행이 가능하다
    */
    return Arrays.copyOf(this.list, this.size);
    //              copyOf(원본 배열,   복사할 갯수);
  }
  // toArray()를 이용해 size만큼의 주소를 다른 그릇에 담아 주고
  // 주소들이 담긴 list는 그대로 유지할 수 있도록 했다.
  // capacity가 아닌 주소가 담긴 그릇만(size) 가져와서 훨씬 더 간결해짐

  public void add(Board board) {
    if (this.size == this.list.length) {
      // 현재 배열에 게시글 객체가 꽉 찼으면
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      /*
      Board[] arr = new Board[newCapacity];
      // 기존의 배열 갯수에 반 만큼을 더 추가
      // 새로 생길 때마다 기존의 배열은 garbage가 된다->linkedList
      for (int i = 0; i < this.list.length; i++) {
        arr[i] = this.list[i];
      }
      this.list = arr;
      // -----아래의 한 줄로 한 번에 실행이 가능하다
      */
      this.list = Arrays.copyOf(this.list, newCapacity);
      System.out.printf("새 배열을 %d개 생성하였음!\n", newCapacity);
    }
    this.list[this.size++] = board;
    // 현재 사이즈 갯수를 그대로 지정한 후 따로 증가시킴
  }

  public Board get(int no) {
    // Board board = null;
    for (int i = 0; i < this.size; i++) {
      if (this.list[i].getNo() == no) {
    // board = this.list[i];
    // break;
        return this.list[i];
      }
    }
    return null;    // 반복문 다 돌아도 못찾았으면 null 리턴
  }
}