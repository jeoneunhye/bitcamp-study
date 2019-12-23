package com.eomcs.basic.ex07.assignment;

public class Test01 {
  public static void main(String[] args) {
    // 191220
    // 배열의 값 중에서 최대값을 출력하라
    // => 출력결과 78
    int[] values = {34, 4, -3, 78, 12, 22, 45, 0, -22};
    // 동일 int[] values = new int[] {34, 4, -3, 78, 12, 22, 45, 0, -22};
    int value = max(values);    // 메서드 호출 : max라는 메소드에게 values 아규먼트의 주소를 넘기고 메서드를 실행해라
    // max
    System.out.println(value);
  }  

  static int max(int[] values) {    //int[] values : 파라미터
    // 파라미터로 배열(주소)을 받고, 그 값 중에서 최대값을 찾아 리턴한다.
    int maxValue = values[0];   // 비교해나갈 변수 선언
    for (int i = 1; i < values.length; i++) {   // 배열.length : 배열의 갯수를 리턴
      if (values[i] > maxValue) {
        maxValue = values[i];
      }
    }
    return maxValue;    // ★for문이 끝나고 리턴
  }
}
