package com.eomcs.oop.ex01.assignment;

import java.util.Scanner;

//사용자로부터 입력 받은 성적 데이터(이름, 국어, 영어, 수학)를 클래스 문법을 이용하여 만든 메모리에 저장하라.
//또한 평균, 합계를 구하고 출력하라. 입력과 출력에 반복문을 적용하라!
//## 실행 결과
//```
//> java -classpath bin step03.assignment.Test01
//입력: 홍길동 100 100 100
//입력: 임꺽정 90 90 90
//입력: 유관순 80 80 80
//--------------------------
//홍길동 100 100 100 300 100.0
//임꺽정  90  90  90 270  90.0
//유관순  80  80  80 240  80.0
//>
//``` 
public class Test01 {
  public static void main(String[] args) {
    // scanner 배열 반복문 사용
    class Score {
      String name;
      int kor;
      int eng;
      int math;
      int sum;
      float avg;
    }
    int SIZE = 100;
    int count = 0;
    Score[] scores = new Score[SIZE];

    Scanner keyScan = new Scanner(System.in);

    for (int i = 0; i < SIZE; i++) {
      System.out.print("입력: ");
      Score score = new Score();    // score 객체

      score.name = keyScan.next(); //   nextLine() : 엔터만 돼 next() : 다돼
      if (score.name.equalsIgnoreCase("quit")) {
        break;
      }
      score.kor = keyScan.nextInt();
      score.eng = keyScan.nextInt();
      score.math = keyScan.nextInt();
      keyScan.nextLine();
      score.sum = score.kor + score.eng + score.math;
      score.avg = score.sum / 3f;

      scores[count++] = score;
    }
    keyScan.close();
    // 출력
    System.out.println("----------------------");
    for (int i = 0; i < count; i++) {
      Score s = scores[i];
      System.out.printf("%s %d %d %d %d %f", s.name, s.kor, s.eng, s.math, s.sum, s.avg);
    }
    System.out.println();
  }
}