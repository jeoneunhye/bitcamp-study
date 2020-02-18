package com.eomcs.util;
// Stack 객체에서 Iterator 규칙에 따라 값을 꺼내주는 클래스를 정의
public class StackIterator<E> implements Iterator<E> {
  Stack<E> stack;

  public StackIterator(Stack<E> stack) {
    this.stack = stack.clone();
    // stack을 복제해서 저장! 꺼내는 순간(pop) 제거되기 때문에
  }

  @Override
  public boolean hasNext() {
    return !stack.empty(); // 비어있지 않다면 true!
  }

  @Override
  public E next() {
    return stack.pop();
  }
}