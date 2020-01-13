package com.eomcs.algorithm.data_structure.linkedlist;

public class LinkedListTest {
  public static void main(String[] args) {
    LinkedList<String> list = new LinkedList<>();
    
    list.add("aaa");
    list.add("bbb");
    list.add("ccc");
    list.add("ddd");
    list.add("eee");
    list.add("fff");
    list.add("ggg");
    
    System.out.println(list.set(0,  "xxx"));
    System.out.println();
//    list.remove(0);
//    list.add(0, "xxx");
    print(list);
    
//    list.add(6, "xxx"); // ggg가 뒤로 밀려남
//    list.add(7, "xxx"); // 유효하지 않은 값 null 아무일도 안일어남
//    print(list);
  }
  
  static void print(LinkedList<String> list) {
    // String/*Object*/[] arr = list.toArray(new String[0]);/*list.toArray();*/
    // 위와 같은 문장
    String[] arr = new String[list.size()];
    list.toArray(arr);
    
    for (String/*Object*/ value : arr) {
      System.out.println(value);
    }
    
//    for (int i = 0; i < list.size; i++) {
//      System.out.println(list.get(i));
//    }
  }
}