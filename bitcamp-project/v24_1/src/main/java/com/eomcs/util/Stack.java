package com.eomcs.util;

import java.util.Arrays;

public class Stack<E> implements Cloneable { // 복제가 가능하다!
  private static final int DEFAULT_CAPACITY = 10;

  Object[] elementData;
  int size;

  public Stack() {
    this.elementData = new Object[DEFAULT_CAPACITY]; // '주소'의 배열을 저장
    this.size = 0;
  }

  public void push(E/*Object*/ value) {
    if (this.size == this.elementData.length) {
      grow();
    }

    this.elementData[size++] = value;
  }

  private void grow() {
    this.elementData = Arrays.copyOf(this.elementData, newCapacity());
  }

  private int newCapacity() {
    int oldCapacity = this.elementData.length;
    return oldCapacity + (oldCapacity >> 1);
  }

  @SuppressWarnings("unchecked")
  public E/*Object*/ pop() {
    if (this.empty())
      return null;

    E/*Object*/ value = (E) this.elementData[--this.size];
    this.elementData[this.size] = null; // 주소를 잃은 배열 값이 garbage가 되게끔 null로 만듦

    return value; // 해당 size의 배열 주소를 리턴
  }

  public boolean empty() {
    return this.size == 0;
  }

  // 생성방법: ctrl+space, public으로 넓히고 리턴 타입을 Stack으로 변경

  // Object.clone()의 'shallow (얕은) copy' 이용하여 스택 객체 복사하기
  // 객체의 인스턴스 변수를 그대로 복제한다.
  // 인스턴스 변수가 가리키는 객체는 복제하지 않는다.
  // 문제점?
  // 따라서 인스턴스 변수 elementData 데이터가 가리키는 배열은 복제하지 않는다.
  // 그래서 배열의 값을 바꾸면 원본 스택에도 영향을 끼친다.
  /*
  @Override
  public Stack clone() {
    // 예외처리문법 try-catch
    try {
      return (Stack) super.clone(); // 예외가 발생한 코드를 객체에 담아 리턴
    } catch (CloneNotSupportedException ex) { // 오류정보 처리를 위해 생성된 블럭 ex
      // Object의 clone() 메서드는
      // 복제가 허용된 객체에 대해서만 해당 인스턴스 변수를 복제한다.
      // 허용되지 않은 객체에 대해서 clone()을 호출하면
      // CloneNotSupportedException 실행 오류가 발생한다.
      // 해결책?
      // 해당 클래스의 객체를 복제할 수 있다고 표시하라.
      // 방법: Cloneable 인터페이스를 지정한다.
      // ex) class My implements Cloneable {...}
      // clone()을 재정의할 경우 반드시 Cloneable 인터페이스를 지정해야 한다.

      System.out.println(ex); // 예외정보를 문자열로 담아 출력
      return null; // 클론하다가 예외(문제)가 발생하면 null을 리턴
    }
  }
   */

  // 문제점: 다시 history를 실행하면 pop하면서 null이 된 주소값을 보여줌
  //      -> 인스턴스 변수 뿐만 아니라 배열 자체도 복제하도록 clone() 오버라이딩 필요

  // 'deep copy'를 이용하여 객체 복제하기
  // 데이터가 들어있는 배열도 그대로 복제한다.
  // 따라서 배열의 값을 바꾸더라도 원본 객체에 영향을 끼치지 않는다.
  @SuppressWarnings("unchecked")
  @Override
  public Stack<E> clone() {
    try {
      Stack<E> temp = (Stack<E>) super.clone();
      // 'shallow copy'를 통해 이 객체의 인스턴스 변수를 그대로 복제한다.

      Object[] arr = new Object[this.size];

      for (int i = 0; i < this.size; i++) {
        arr[i] = this.elementData[i];
      }
      // elementData 배열을 복제한다.
      // 배열만 복제하고 그 배열에 저장된 객체(ex: 문자열, Member 등)까지는 복제하지 않는다.(공유)
      // 어디까지 복제할 것인지는(deep copy의 수준) 상황에 따라 결정한다.

      temp.elementData = arr;
      // 복제한 스택 객체가 새로 만든 배열을 가리키도록 한다.

      return temp; // 새로 만든 배열을 사용하는 것!
    } catch (CloneNotSupportedException ex) {
      System.out.println(ex);
      return null;
    }
  }
  
  public Iterator<E> iterator() {
    return new StackIterator<E>(this); // Stack this를 넘겨준다.
  }
  
  static class StackIterator<E> implements Iterator<E> {
    Stack<E> stack;
    //int cursor; index를 필요로 하지 않기 때문에 사용하지 않는다.

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
}