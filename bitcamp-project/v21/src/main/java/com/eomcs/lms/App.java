package com.eomcs.lms;

import java.util.Scanner;
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;
import com.eomcs.util.Prompt;
import com.eomcs.util.Stack;
public class App {
  static Scanner keyboard = new Scanner(System.in);   
  static Stack<String> commandStack = new Stack<>();

  public static void main(String[] args) {
    Prompt prompt = new Prompt(keyboard);
    
    BoardHandler boardHandler = new BoardHandler(prompt);
    LessonHandler lessonHandler = new LessonHandler(prompt);
    MemberHandler memberHandler = new MemberHandler(prompt);
    //java.util.ArrayList list;
    String command;
    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();
      
      if (command.length() == 0)
        continue;
      
      commandStack.push(command); // 사용자가 입력할 때마다 쌓임
      
      switch (command) {
        case "/lesson/add":
          lessonHandler.addLesson();
          break;
        case "/lesson/list":
          lessonHandler.listLesson();
          break;
        case "/lesson/detail":
          lessonHandler.detailLesson();
          break;
        case "/lesson/update":
          lessonHandler.updateLesson();
          break;
        case "/lesson/delete":
          lessonHandler.deleteLesson();
          break;
        case "/member/add":
          memberHandler.addMember();
          break;
        case "/member/list":
          memberHandler.listMember();
          break;
        case "/member/detail":
          memberHandler.detailMember();
          break;
        case "/member/update":
          memberHandler.updateMember();
          break;
        case "/member/delete":
          memberHandler.deleteMember();
          break;
        case "/board/add":
          boardHandler.addBoard();
          break;
        case "/board/list":
          boardHandler.listBoard();
          break;
        case "/board/detail":
          boardHandler.detailBoard();
          break;
        case "/board/update":
          boardHandler.updateBoard();
          break;
        case "/board/delete":
          boardHandler.deleteBoard();
          break;
        case "history":
          printCommandHistory();
          break;
        default:
          if (!command.equalsIgnoreCase("quit")) {
            System.out.println("실행할 수 없는 명령입니다.");
          }
      }
    } while (!command.equalsIgnoreCase("quit"));
    
    System.out.println("안녕!");
    keyboard.close();
  }
  
  private static void printCommandHistory() {
    Stack<String> historyStack = commandStack.clone();
    
    int count = 0;
    
    while (!historyStack.empty()) {
      System.out.println(historyStack.pop());
      count++;
      
      if (count % 5 == 0) { // 5번 출력할 때마다 계속할 지 묻기
        System.out.print(":");
        String str = keyboard.nextLine();
        if (str.equalsIgnoreCase("q")) // q를 입력받으면 종료, 아니면 계속
          break;
      }
    }
  }
}