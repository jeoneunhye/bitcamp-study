package com.eomcs.basic.ex04;

public class Exam31 {
public static void main(String[] args) {
  // 부동소수점 변수 - 메모리 크기
  float f;
  f = 3.141592f;  //float 4바이트 리터럴을 선언해도 뒤에 f를 꼭 붙여야함
  System.out.println(f);

  f = 3.1415929f;
  System.out.println(f);  // 3.141593 -> 구겨짐

  f = 0.00000003141592f;
  System.out.println(f);  // 3.141592E-8 ->잘 나옴. 소수점 이하 0을 제외한 갯수가 7자리면 ok

  f = 0.000000031415929f;
  System.out.println(f);  // 3.141593E-8 -> 구겨짐
  
  f = 314159200000.0f;
  System.out.println(f);  // 3.14159202E11 -> 소수점 이상의 0이면 유효자릿수에 포함됨. 정수값이 너무 커서 구겨짐

  double d;
  d = 9.87654321234567;
  System.out.println(d);  // 9.87654321234567 -> 잘 나옴

  d = 98765432.1234567;
  System.out.println(d);  // 9.87654321234567E7 -> 잘 나옴

  d = 98765432123456.7;
  System.out.println(d);  // 9.87654321234567E13 -> 잘 나옴

  d = 98765432123456.74;
  System.out.println(d);  // 9.876543212345673E13 -> 구겨짐

  // 변수와 리터럴
  f = 99999.88f;
  System.out.println(f);  // 99999.88

  f = 99999.88777f; // 이미 리터럴이 4바이트 유효자릿수를 넘어갔다.
  System.out.println(f);  // 99999.89

  d = 99999.88f;  // 적은 값이라도 가능하면 8바이트 메모리를 사용하자(정수 리터럴과 사용의 차이가 있다)
  System.out.println(d);  // 99999.8828125

  float f1 = 99988.88f;
  float f2 = 11.11111f;
  System.out.println(f1); // 99988.88
  System.out.println(f2); // 11.11111

  float f3 = f1 + f2;
  System.out.println(f3); // 99999.99
  // 99988.88
  //    11.11111
  //------------
  // 99999.99111  -> 값이 잘림
  // 정상적인 값이 나오게 하려면?
  double r = f1 + f2; // float + float = float 이미 결과에서 값이 왜곡된다.
  System.out.println(r);  // (X) 99999.9921875

  double d1 = 99988.88;
  double d2 = 11.11111;
  System.out.println(d1); // 99988.88
  System.out.println(d2); // 11.11111

  double d3 = d1 + d2;
  System.out.println(d3); // (O) 99999.99111

  // ★계산을 요구하거나 웬만한 것 : double 사용
  // 몸무게, 키 : float 사용

  f1 = 3.141592f;
  d1 = f1;  // 컴파일은 됨. ★단 값이 왜곡'될 수 있다.'
  System.out.println(d1); // 3.141592025756836

  //f1 = d1;  컴파일 오류 : possible lossy conversion from double to float
  }
}