// 메서드 오버로딩(overloading) - 문법 사용 후
package com.eomcs.oop.ex06.b;

public class Exam0120 {
  static class Calculator {
    // 파라미터의 타입이나 갯수가 다르더라도
    // 같은 일을 하는 메서드에 대해서는 같은 이름을 갖게 한다.
    static int plus(int a, int b) {
      return a + b;
    }
    
    static int plus(int a) {
      return a + a;
    }
    
    static float plus(float a, float b) {
      return a + b;
    }
  }
  
  public static void main(String[] args) {
    // 호출하는 메서드의 이름은 같지만
    // 아규먼트의 타입이나 갯수에 따라 호출되는 메서드가 결정된다.
    int r1 = Calculator.plus(100, 200);
    int r2 = Calculator.plus(100);
    float r3 = Calculator.plus(35.7f, 22.2f);
    
    System.out.printf("%d, %d, %.1f\n", r1, r2, r3);
    
    // 오버로딩(overloading)?
    // 파라미터의 형식은 다르지만 같은 기능을 수행하는 메서드에 대해 
    // 같은 이름을 부여함으로써 프로그래밍의 일관성을 제공하기 위한 문법이다.  
  }
}

// 오버로딩(overloading)
// 파라미터 타입이나 개수, 순서는 다르지만 이름이 같은 메서드를 여러 개 만들 수 있는 문법
// - 왜 이런 문법이 등장했는가?
// 파라미터의 형식은 다르지만 ★같은 기능을 수행하는 메서드에 대해
// 같은 이름을 부여함으로써 프로그래밍의 일관성을 제공하기 위함
// C는 각각의 메서드를 접두사나 접미사를 붙여 각각의 이름으로 따로 만들어줘야 한다.