package com.eomcs.algorithm.data_structure.array;

public class ArrayList2Test {
  public static void main(String[] args) {
  ArrayList2 a = new ArrayList2();
  a.add("aaa");
  a.add("bbb");
  a.add("ccc");
  a.add("ddd");
  a.add("eee");
  a.add("fff");
  
  a.remove(0);
  
  print(a);
  
  }
  
  public static void print(ArrayList2 arr) {
    for (int i = 0; i < arr.size(); i++) {
      System.out.println(arr.get(i));
    }
  }
}