package com.eomcs.oop.ex01;

//# 클래스 - 사용 전
//
public class Exam0110 {
  public static void main(String[] args) {
    // 낱개의 변수를 사용하여 한 사람의 성적 정보를 저장하라!
    // => 식탁에 밥, 국, 반찬1, 반찬2, 반찬3 을 각각 따로 가져오는 상황.
    String name;
    int kor;
    int eng;
    int math;
    int sum;
    float aver;

    name = "홍길동";
    kor = 100;
    eng = 90;
    math = 85;
    sum = kor + eng + math;
    //aver = sum / 3;   // 91.0
    aver = sum / (float)3; // 91.7 소수점 이하가 나오도록 하는 방법
    //aver = (float)sum / (float)3; // 91.7
    //aver = (float)sum / 3; 
    // float 임시메모리가 만들어지고 sum의 값을 넣음. sum의 값은 바뀌지 않기 때문에 둘다 명시하지 않아도 된다(암시적 형변환)
    // 두 타입이 맞지 않으면 연산이 불가능하기 때문에 암시적 형변환이 된다.
    System.out.printf("이름: %s\n", name);
    System.out.printf("국어: %d\n", kor);
    System.out.printf("영어: %d\n", eng);
    System.out.printf("수학: %d\n", math);
    System.out.printf("합계: %d\n", sum);
    System.out.printf("평균: %.1f\n", aver);
  }
}