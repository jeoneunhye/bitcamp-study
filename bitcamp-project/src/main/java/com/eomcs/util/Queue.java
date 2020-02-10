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
    return new QueueIterator<E>(this);
    // static 클래스인 QueueIterator는 내장 변수 this를 가지고 있지 않다.
    // 그래서 호출하는 쪽에서 파라미터로 Queue 구현체를 넘겨줘야 한다.
  }

  // QueueIterator 클래스는 Queue 클래스의 메서드에서만 필요로 하는 클래스
  // => 이동하여 static nested 클래스로 변경했다.
  // new Queue(); 하면 중첩 클래스인 QueueIterator 객체는 생성되지 않는다.
  /*public*/ static class QueueIterator<E> implements Iterator<E> {
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