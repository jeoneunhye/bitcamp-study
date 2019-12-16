package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;
public class App {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);   //interface(매개) 키보드와 연결 용어
    // 키보드에서 사용자가 입력한 값을 읽어 문자열이나 정수, 부동소수점 등으로 리턴하는 역할
    
    System.out.print("번호? ");    //print : 입력한 값을 줄바꿈하지 않고 출력
    int no = keyboard.nextInt();
    //String : 문자열 담을 메모리 설정 no : reference 메모리 이름 사용자가 한 줄을 입력할 때까지 기다림 엔터를 쳐야 읽기를 멈춤
    // = : assignment 입력하면 no라는 메모리에 집어넣음 할당 연산자
    //out이라는 도구에게 println할 것을 지시
    
    keyboard.nextLine();    // 정수 값 다음에 있는 줄바꿈 기호를 제거하는 용이다.
    System.out.print("수업명? ");
    String title = keyboard.nextLine();
    System.out.print("수업내용? ");
    String description = keyboard.nextLine();
    System.out.print("시작일? ");
    // "yyyy-MM-dd" 형태로 입력된 문자열을 날짜 정보로 바꾼다.
    // 형식에 어긋나게 입력하면 오류 발생
    Date startDate = Date.valueOf(keyboard.next());
    System.out.print("종료일? ");
    Date endDate = Date.valueOf(keyboard.next());
    System.out.print("총수업시간? ");
    int totalHours = keyboard.nextInt();
    System.out.print("일수업시간? ");
    int dayHours = keyboard.nextInt();

    System.out.println();
    
    System.out.printf("번호: %d\n", no);  //%s 메모리 값이 들어갈 자리 \ n 줄바꿈 이스케이프 문자
    System.out.println("수업명: " + title);
    System.out.println("수업내용: " + description);
    System.out.printf("기간: %s ~ %s\n", startDate, endDate);
    System.out.printf("총수업시간: %d 시간\n", totalHours);
    System.out.println("일수업시간: " + dayHours + " 시간");
    
    keyboard.close();   //운영체제에게 자원을 돌려줌
  }
}
