package com.eomcs.basic.ex06.assignment;

//import java.util.ScannerTest03.java;

//사용자로부터 정삼각형 밑변의 길이를 숫자를 입력 받아 '*' 문자로 그려라.
//반복문을 사용할 때는 while 또는 do ~ while 문만을 사용하라!
/*
실행 결과
```
밑변 길이? 5
  *
 ***
*****
``` 
 */

// 현재 과제와 가장 유사한 결과를 내는 소스를 가져와서 편집한다. (Test01)
public class Test03 {
  public static void main(String[] args) {
    int width = Console.inputInt();
    if (width % 2 == 0)    // 밑변의 길이가 짝수면 증가시키든 감소시키든 홀수로 만들어라
      width--;                //  (짝수 실행 시의 불필요한 앞여백을 제거)
    
    int spaceSize = width >> 1; // 공백만들 변수 준비 = width / 2 보다 효율적인 방법
    int line = 0;
    while (line++ < width) {
      if (line % 2 == 0) {
        continue;   // 짝수면 다음 라인으로 건너뛰어라
      }
      Graphic.drawLine(spaceSize, ' ');
      spaceSize--;  // 2개에서 1개로 줄임
      
      Graphic.drawLine(line, '*');  // char이므로 "*" 안됨 !
      System.out.println();
      }
  }
  // 클래스 안에/ 메인 메서드 밖에
//  static int inputInt() {        // 사용자의 입력값 int를 받는 클래스
//    Scanner keyScan = new Scanner(System.in);
//    System.out.print("밑변 길이? ");
//    int width = keyScan.nextInt();
//    keyScan.close();
//    return width;   // 메서드를 실행하면 width값이 놓임 -> main 블럭에 변수 선언이 필요 (int width = )
//  }

//  static void drawLine(int length, char ch) {    // +char 출력할 문자를 받음
//    int x = 0;
//    while (x++ < length) {
//      System.out.print(ch);
//    }
//  }
}
