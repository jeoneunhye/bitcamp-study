package com.eomcs.basic.ex07.assignment;

public class Test03 {
  public static void main(String[] args) {
    // 191220
    // 배열에 들어있는 값의 순서를 거꾸로 변경하라.
    // => 출력 결과 -22, 0, 45, 22, 12, 78, -3, 4, 34
    int[] values = {34, 4, -3, 78, 12, 22, 45, 0, -22};

    reverse(values);

    printValues(values);
  }
  // reverse 메서드 생성
  static void reverse(int[] values) {
    int count = values.length >> 1;
    int temp = 0;
    int endPos = values.length - 1; // endPosition. j를 values.length - 1로 표현한 것
    for (int i = 0; i < count; i++) {    // j는 좌표가 거꾸로 온다 i와 j값 교차
      temp = values[i];
      values[i] = values[endPos - i];    // i와 교환할 것 j에 i를 빼준다 (동일하게 교차되는 위치와 교체하기 위해)
      values[endPos - i] = temp;
    }
  }

  static void printValues(int[] values) {
    for (int i = 0; i < values.length; i++) {
      System.out.printf("%3d ", values[i]);
    }
    System.out.println();
  }
}