package com.eomcs.util;
// Queue 객체에서 Iterator 규칙에 따라 값을 꺼내주는 클래스를 정의
public class QueueIterator<E> implements Iterator<E> {
  Queue<E> queue;

  public QueueIterator(Queue<E> queue) {
    this.queue = queue.clone();
    // queue를 복제해서 저장! 꺼내는 순간(poll) 제거되기 때문에
  }

  @Override
  public boolean hasNext() {
    return queue.size() > 0; // 한 개라도 있다면 true!
  }

  @Override
  public E next() {
    return queue.poll();
  }
}