package com.eomcs.basic.ex07.assignment;

public class Test02 {
  public static void main(String[] args) {
    // 191220
    // 배열에 들어있는 값을 오름차순으로 정렬하라
    // => 출력 결과 -22, -3, 0, 4, 12, 22, 34, 45, 78
    int[] values = {34, 4, -3, 78, 12, 22, 45, 0, -22};
    sort(values);
    for (int value : values) {
      System.out.println(value);
    }
  }
  // sort 오름차순 정렬 메소드 생성

  static void sort(int[] values) {
    int temp = 0;
    for (int i = 0; i < values.length; i++) {
      for (int j = 0; j < values.length -1; j++) {

        if (values[j] > values[j + 1]) {
          temp = values[j];
          values[j] = values[j + 1];
          values[j + 1] = temp;
        }
      }
    }
  }
}

