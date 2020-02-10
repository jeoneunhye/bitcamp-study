package com.eomcs.util;

public class Queue<E> extends LinkedList<E> implements Cloneable {
  public void offer(E value) {
    this.add(value);
  }

  public E poll() {
    return this.remove(0);
  }

  @Override
  public Queue<E> clone() {
    Queue<E> temp = new Queue<E>();

    for (int i = 0; i < this.size(); i++) {
      temp.offer(this.get(i));
    }

    return temp;
  }

  // 로컬 클래스 -> 익명 클래스
  // 인스턴스를 한 개만 생성할 거면 익명 클래스로 정의하라.
  // Queue.java.01의 리턴 값에 obj 구현체를 바로 집어 넣음
  @Override
  public Iterator<E> iterator() {
    // Iterator<E> obj =
    // 익명 클래스
    // return obj;

    return new Iterator<E>() {
      Queue<E> queue;

      {
        this.queue = Queue.this.clone();
      }

      @Override
      public boolean hasNext() {
        return queue.size() > 0;
      }

      @Override
      public E next() {
        return queue.poll();
      }
    };
  }
}