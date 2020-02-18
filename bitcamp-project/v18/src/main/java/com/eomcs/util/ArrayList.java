package com.eomcs.util;
// v18 제네릭문법- 객체를 특정하는 기능이 있어 공통된(일반적인) 형태로 코드 작성 가능
// 한 개 클래스를 이용하여 손쉽게 다용도로 처리할 수 있다.
import java.util.Arrays;
import com.eomcs.lms.domain.Board;

public class ArrayList<E> {
  // <E> 특정 데이터를 다루는 클래스 이름을 받겠다. 데이터 타입 선언 필요x
  // 외부에서 알려주는 변수명 'T'ype 목록의 한 항목 'E'lement
  static final int DEFAULT_CAPACITY = 3; 

  Object[] list; // E가 아니다.
  int size;

  public ArrayList() {
    this.list = new Object[DEFAULT_CAPACITY];
  }

  public ArrayList(int capacity) {
    if (capacity > DEFAULT_CAPACITY &&
        capacity < 100000) {
      this.list = new Object[capacity];
    } else {
      this.list = new Object[DEFAULT_CAPACITY];
    }
  }

//  public Object[] toArray() {
//    return Arrays.copyOf(this.list, this.size);
//    // Object 배열을 만든다.
//  }

  @SuppressWarnings("unchecked")
  public E[] toArray(E[] arr) {
    if (arr.length < this.size) {
      return (E[]) Arrays.copyOf(this.list, this.size, arr.getClass());
      // 기존의 list를 size만큼 복사. getClass 배열의 타입지정
    }
    System.arraycopy(this.list, 0, arr, 0, this.size); // 값을 저장할 만큼 충분한 크기라면 return
    // size만큼 복사하기때문에 size보다 넘쳐나면 조회시 에러가 뜬다.
    
    /*
    // 위의 arraycopy()는 다음 코드와 같다.
    for (int i = 0; i < this.size; i++) {
      arr[i] = (E) this.list[i];
    }
     */
    return arr; // 리턴값도 배열 주소와 같다.
    //return (E[]) Arrays.copyOf(this.list, this.size, arrayType);
    // Object 배열을 만들지 않도록 앞에 (E[])를 붙여준다.
    
    // copyOf() 메서드야
    // arrayType에 지정된 배열을 size만큼 만들어라
    // 그리고 list 배열에 저장된 주소를 새로 만든 배열에 복사하라
    // 마지막으로 새로 만든 배열의 주소를 리턴하라
  }
  
  public void add(E/*Object*/ obj) {
    if (this.size == this.list.length) {
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      this.list = Arrays.copyOf(this.list, newCapacity);
    }
    this.list[this.size++] = obj;
  }

  @SuppressWarnings("unchecked") // '안전하지 않다'경고메시지 띄우지 않기
  public E/*Object*/ get(int idx) {
    if (idx >= 0 && idx < this.size) {
      return (E)this.list[idx]; // 노란 밑줄 사라짐
    } else {
      return null;
    }
    
  }
  public int size() { // size 변수가 default이므로 메서드 생성
    return this.size;   // heap에 있는 size 사용할 것
  }
}