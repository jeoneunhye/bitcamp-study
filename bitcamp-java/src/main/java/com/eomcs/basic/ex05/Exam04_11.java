// 비트 이동 연산자 : 비트 이동의 유효 범위
package com.eomcs.basic.ex05;

public class Exam04_11 {
  public static void main(String[] args) {
    System.out.println(3 << 1);
    //     00000000 00000000 00000000 00000011  = 3
    //   0|0000000  00000000 00000000 00000011x
    //     00000000 00000000 00000000 000000110 = 6
    
    System.out.println(3 << 33);    // 6
    System.out.println(3 << 65);    // 6
    System.out.println(3 << 97);    // 6
    // 3에 33비트를 이동하나, 65비트를 이동하나, 97비트를 이동하나
    // 같은 값이 나오는 이유?
    // => int 타입의 값에 대해 비트 이동을 할 때에는 0 ~ 31까지만 유효하다.
    //    만약 31를 넘는 경우 32로 나눈 나머지 값을 비트 이동으로 간주한다.
    // => long 타입의 값에 대해 비트 이동을 할 때에는 0 ~ 63까지만 유효하다.
    //    만약 63를 넘는 경우 64로 나눈 나머지 값을 비트 이동으로 간주한다.
    
    //    공식)
    //    n << s
    //    n이 int 타입이라면 다음 계산을 통해 s의 최종 값을 결정한다.
    //      s & 0b11111 = 최종 비트 이동 값
    //    따라서 s의 값은 무조건 0 ~ 31이다.
    //    결국 s의 값은 s % 32의 결과와 같다.
    
    //    n이 long 타입이라면 다음 계산을 통해 s의 최종 값을 결정한다.
    //      s & 0b111111 = 최종 비트 이동 값
    //    따라서 s의 값은 무조건 0 ~ 63이다.
    //    결국 s의 값은 s % 64의 결과와 같다.
    
    //  예1)
    //  3 << 33
    //  n = 00000000 00000000 00000000 00000011 = 3
    //  s = 00000000 00000000 00000000 00100001 = 33
    //  비트이동 => s & 0b11111
    //      00000000 00000000 00000000 00100001
    //    & 00000000 00000000 00000000 00011111
    //    -------------------------------------
    //      00000000 00000000 00000000 00000001 = 1 (결국 33을 2로 나눈 나머지)
    // 최종 비트 이동 값을 계산하면 다음과 같다.
    //  3 << 33 == 3 << 1
    
    //  예2)
    //  3 << 65
    //  n = 00000000 00000000 00000000 00000011 = 3
    //  s = 00000000 00000000 00000000 01000001 = 65
    //  비트이동 => s & 0b111111
    //      00000000 00000000 00000000 01000001
    //    & 00000000 00000000 00000000 00111111
    //    -------------------------------------
    //      00000000 00000000 00000000 00000001 = 1
    // 최종 비트 이동 값을 계산하면 다음과 같다.
    //  3 << 65 == 3 << 1
    
    // 비트 이동 계산의 근거: Java language specification의 Shift Operation
    // If the promoted type of the left-hand operand is int, 피연산자 값이 int라면
    // then only the five lowest-order bits 오직 하위(오른쪽편) 5비트만 사용한다.
    // of the right-hand operand are used as the shift distance.
    // It is as if the right-hand operand were subjected
    // to a bitwise logical AND operator & (§15.22.1) 비트 연산자 &를 사용하여
    // with the mask value 0x1f (0b11111). 0b11111을 마스킹 값으로 사용
    // The shift distance actually used is therefore always 유효 범위가 0 ~ 31까지다.
    // in the range 0 to 31, inclusive.
    
    // if the promoted type of left-hand operand is long, 피연산자의 값이 long이라면
    // then only the six lowest-order bits 오직 하위(오른쪽편) 6비트만 사용한다.
    // of the right-hand operand are used as the shift distance.
    // It is as if the right-hand operand were subjected
    // to a bitwise logical AND operator & (§15.22.1) 비트 연산자 &를 사용하여
    // with the mask value 0x1f (0b111111). 0b111111을 마스킹 값으로 사용
    // The shift distance actually used is therefore always 유효 범위가 0 ~ 63까지다.
    // in the range 0 to 63, inclusive.
  }
}