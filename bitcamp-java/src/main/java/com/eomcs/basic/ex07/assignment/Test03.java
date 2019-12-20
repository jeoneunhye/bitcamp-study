package com.eomcs.basic.ex07.assignment;

public class Test03 {
  public static void main(String[] args) {
    // 191220
    // 배열에 들어있는 값의 순서를 거꾸로 변경하라.
    // => 출력 결과 -22, 0, 45, 22, 12, 78, -3, 4, 34
    int[] values = {34, 4, -3, 78, 12, 22, 45, 0, -22};
    int[] v = reverse(values);
    for (int value : values) {
      System.out.println(value);
    }
  }
  // reverse 메서드 생성
  static int[] reverse(int[] values) {
    for (int i = 0; i < values.length / 2; i++) {
      int temp = 0;
      temp = values[i];
      values[i] = values[values.length-i-1];
      values[values.length-i-1] = temp;
    }
    return values;
  }
}