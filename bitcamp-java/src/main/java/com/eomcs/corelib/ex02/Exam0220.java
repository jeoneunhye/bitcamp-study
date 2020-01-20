// Wrapper 클래스 - auto-boxing/auto-unboxing -> Exam0221.java, Exam0222.java
package com.eomcs.corelib.ex00;

public class Exam0220 {
  public static void main(String[] args) {
    // int ==> Integer
    int i1 = 100;
    Integer obj1 = Integer.valueOf(i1);

    // Integer ==> int
    Integer obj2 = Integer.valueOf(200);
    int i2 = obj2.intValue();
  }
}