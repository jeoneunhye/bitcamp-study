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
    return new QueueIterator<>(this);
  }

  // QueueIterator 클래스는 Queue 클래스에서만 사용하다.
  // => static nested 클래스로 만들었다.
  // new Queue(); 하면 중첩 클래스인 QueueIterator 객체는 생성되지 않는다.
  static class QueueIterator<E> implements Iterator<E> {
    Queue<E> queue;

    public QueueIterator(Queue<E> queue) {
      this.queue = queue.clone();
    }

    @Override
    public boolean hasNext() {
      return queue.size() > 0;
    }

    @Override
    public E next() {
      return queue.poll();
    }
  }
}