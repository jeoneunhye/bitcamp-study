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
    // QueueIterator 클래스는 iterator() 메서드 안에서만 사용되는 중첩 클래스다.
    // 멤버 클래스 QueueIterator를 Iterator()의 로컬 클래스로 위치 이동
    class QueueIterator<T> implements Iterator<T> {
      Queue<T> queue;

      @SuppressWarnings("unchecked")
      public QueueIterator() {
        this.queue = (Queue<T>) Queue.this.clone();
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

    return /*this.*/new QueueIterator<E>();
    // QueueIterator는 더이상 Queue의 멤버 클래스가 아니기 때문에
    // 인스턴스를 생성할 때 바깥 클래스의 인스턴스 주소를 주면 안 된다.
    // 즉 생성자를 호출하는 앞쪽에 this를 붙여서는 안 된다.
  }
}