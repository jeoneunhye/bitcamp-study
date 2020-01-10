package com.eomcs.util;
import java.util.Arrays;
import com.eomcs.lms.domain.Board;

public class ArrayList<E> {
  static final int DEFAULT_CAPACITY = 3; 

  Object[] list;
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
  public E get(int idx) {
    if (idx >= 0 && idx < this.size) {
      return (E)this.list[idx]; // 노란 밑줄 사라짐
    } else {
      return null;
    }
  }

  public E set(int index, E obj) {
    if (index < 0 || index >= this.size) // 유효 범위를 벗어나면
      return null;
    
    @SuppressWarnings("unchecked")
    E old = (E)this.list[index];
    this.list[index] = obj;
    
    return old;
  }

  @SuppressWarnings("unchecked")
  public E remove(int index) {
    if (index < 0 || index >= this.size) // 유효 범위를 벗어나면
      return null;
    
    E old = (E)this.list[index];
    // 삭제할 항목을 따로 보관해둔다.
    
    for (int i = index + 1; i < this.size; i++) {
      this.list[i - 1] = this.list[i];  // i방을 i앞 방으로 복사
    }
    
    this.size--;
    
    this.list[this.size] = null;
    
    return old;
    // 삭제한 항목을 리턴한다.
  }
  
  public int size() { // size 변수가 default이므로 메서드 생성
    return this.size;   // heap에 있는 size 사용할 것
  }
  
  public static void main(String[] args) {
    ArrayList<String> list = new ArrayList<>();
    list.add("aaaa");
    list.add("bbbb");
    list.add("cccc");
    list.add("dddd");
    list.add("eeee");
    list.add("ffff");
    
    list.remove(0);
    
    /*
    list.set(0, "0000"); // aaaa -> 0000
    list.set(3, "3333"); // dddd -> 3333
    list.set(5, "5555"); // ffff -> 5555
    list.set(-1, "ㅋㅋㅋㅋ"); // 존재하지 않는 배열의 값
    list.set(6, "^^^^^^"); // 존재하지 않는 배열의 값
    */
    
    String[] arr = list.toArray(new String[] {});
    for (String s : arr) {
      System.out.println(s);
    }
  }
}