package com.eomcs.basic.ex03;

// 부동 소수점 리터럴 - 부동소수점을 메모리에 저장하기

public class Exam34 {
  public static void main(String[] args) {
    // 4바이트 부동소수점 유효 자릿수 : 7자리
    System.out.println(3.141592f);  //3.141592    ===> 4바이트 한도 내 값을 찾음 ok
    System.out.println(3141.592f);  //3141.592  ok
    System.out.println(31415.92f);  //31415.92  ok
    System.out.println(314159.2f);  //314159.2  ok
    System.out.println(314159.26f); //314159.25 => 값이 구겨진다
    System.out.println(3.1415926f);  //3.1415925  => 값이 구겨진다
    System.out.println(3.14159265f);  //3.1415927 => 값이 구겨진다
    System.out.println(3.141592653f);  //3.1415927  => 값이 구겨진다
    System.out.println(3.1415926535f);  //3.1415927 => 값이 구겨진다

    // 8바이트 부동소수점 유효 자릿수 : 15자리
    System.out.println(3.141592653589793);  // 3.141592653589793 ok
    System.out.println(31415926.53589793);  // 3.141592653589793E7 ok 10의 7승자리에 소수점이 있다
    System.out.println(314159265358979.3);  // 3.141592653589793E14 ok 10의 14승자리에 소수점이 있다

    // 16자리인 경우 일부 부동소수점의 값이 제대로 저장되지 않는다.
    System.out.println(914159265358979.3);  // 9.141592653589792E14 값이 구겨짐
    System.out.println(91415926.53589793);  // 9.141592653589793E7 ok
    System.out.println(9.141592653589793);  // 9.141592653589793 ok

    // 15자리인 경우 대부분 부동소수점의 값이 제대로 저장된다.
    System.out.println(91415926535897.3);  // 9.14159265358973E13 ok
    System.out.println(91415926.5358973);  // 9.14159265358973E7 ok
    System.out.println(9.14159265358973);  // 9.14159265358973 ok

    // 부동소수점을 저장할 때 완벽하게 저장되지 않는 경우
    System.out.println(7 * 0.1);  // 0.7000000000000001 JVM 보정작업을 통해 해결해야 함

    // 부동소수점을 2진수로 변환하는 규칙
    // IEEE 754   



  }
}