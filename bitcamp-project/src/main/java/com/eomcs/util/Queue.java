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

  @Override
  public Iterator<E> iterator() {
    return this.new QueueIterator<E>(/*this*/);
    // this : non-static 클래스 AbstractList의 인스턴스 주소로
    // 논스태틱 중첩 클래스(이너 클래스)인 ListIterator에 접근
  }

  // QueueIterator non-static nested 클래스로 변경
  /*static*/ class QueueIterator<T> implements Iterator<T> {
    Queue<T> queue;

    @SuppressWarnings("unchecked")
    public QueueIterator(/*Queue<E> queue*/) {
      this.queue = (Queue<T>) Queue.this.clone/*queue.clone*/();
      // 바깥 클래스인 Queue의 인스턴스 주소 this
    }

    @Override
    public boolean hasNext() {
      return queue.size() > 0;
    }

    @Override
    public T next() {
      return queue.poll();
    }
  }
}