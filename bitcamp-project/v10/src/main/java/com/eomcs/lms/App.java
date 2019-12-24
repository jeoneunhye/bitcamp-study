package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;
public class App {
  static Scanner keyboard = new Scanner(System.in);   

  public static void main(String[] args) {
    
    // LessonHandler의 메서드를 사용하기 전에
    // 그 메서드가 필요로 하는 Keyboard 객체를 설정해줘야 한다.
    LessonHandler.keyboard = keyboard;
    // LessonHandler 클래스에서 변수를 선언해놓고
    // main이 쓰는 Keyboard 값을 넘겨준다
    MemberHandler.keyboard = keyboard;
    BoardHandler.keyboard = keyboard;
    
    String command;
    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();
      switch (command) {
        case "/lesson/add":
          LessonHandler.addLesson();
          // 다른 클래스로 분리한 메서드를 호출할 때는
          // 클래스 이름을 지정해야 한다.
          break;
        case "/lesson/list":
          LessonHandler.listLesson();
          break;
        case "/member/add":
          MemberHandler.addMember();
          break;
        case "/member/list":
          MemberHandler.listMember();
          break;
        case "/board/add":
          BoardHandler.addBoard();
          break;
        case "/board/list":
          BoardHandler.listBoard();
          break;
        default:
          if (!command.equalsIgnoreCase("quit")) {
            System.out.println("실행할 수 없는 명령입니다.");
          }
      }
    } while (!command.equalsIgnoreCase("quit"));
    System.out.println("안녕!");
  }
}
