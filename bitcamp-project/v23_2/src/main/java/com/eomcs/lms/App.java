package com.eomcs.lms;

import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;
import com.eomcs.util.ArrayList;
import com.eomcs.util.LinkedList;
import com.eomcs.util.AbstractList;
import com.eomcs.util.Prompt;
import com.eomcs.util.Queue;
import com.eomcs.util.Stack;
public class App {
  static Scanner keyboard = new Scanner(System.in);   
  static Stack<String> commandStack = new Stack<>();
  static Queue<String> commandQueue = new Queue<>();

  public static void main(String[] args) {
    Prompt prompt = new Prompt(keyboard);

    // 단지 유지보수를 좋게 하기 위해 ArrayList와 LinkedList의 공통 분모를 뽑아서
    // 만든 클래스가 List다.
    // List 크래스는 실제 작업을 하는 클래스가 아니다.
    // 그럼에도 불구하고 개발자가 다음과 같이 list 객체를 사용하려 한다면 막을 수 없다.
    // => 실행해도 메서드가 아무런 작업을 수행하지 않을 것이다.
    // => 왜? List 클래스에 정의된 메서드는 아무런 기능이 없다.

    // 해결책
    // 이렇게 Generalization을 통해 만든 클래스의 경우
    // 서브 클래스에게 공통 분모를 물려주기 위한 용도로 사용된다.
    // 이런 류의 클래스는 직접 인스턴스를 생성하지 못하도록 해서
    // 직접 사용하는 것을 막아야 한다.
    // 이런 용도로 사용하는 문법이 "추상 클래스(abstract class)"다.
    
    // List 클래스(AbstractList로 이름 변경함)를 추상 클래스로 만들면
    // 다음과 같이 인스턴스를 생성할 수 없다.
    // 아예 인스턴스 생성을 원천적으로 차단하는 효과가 있다.
    // AbstractList<Board> boardList = new AbstractList<>(); // 컴파일 오류
    
    // 반드시 AbstractList의 일반 하위 객체를 정의해야 한다.
    LinkedList<Board> boardList = new LinkedList<>();
    BoardHandler boardHandler = new BoardHandler(prompt, boardList);
    
    ArrayList<Lesson> lessonList = new ArrayList<>();
    LessonHandler lessonHandler = new LessonHandler(prompt, lessonList);

    LinkedList<Member> memberList = new LinkedList<>();
    MemberHandler memberHandler = new MemberHandler(prompt, memberList);

    String command;
    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();

      if (command.length() == 0)
        continue;

      commandStack.push(command); // 사용자가 입력할 때마다 쌓임
      commandQueue.offer(command);

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
        case "history2":
          printCommandHistory2();
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

  private static void printCommandHistory2() {
    int count = 0;

    /*
    // clone() 재정의 전
    while (commandQueue.size() > 0) {
      System.out.println(commandQueue.poll());

      //count++; 아래와 같음
      // count값을 조사하기 전 미리 증가시키자
      if (++count % 5 == 0) { // 5번 출력할 때마다 계속할 지 묻기
        System.out.print(":");
        String str = keyboard.nextLine();
        if (str.equalsIgnoreCase("q")) // q를 입력받으면 종료, 아니면 계속
          break;
      }
    }
     */

    Queue<String> historyQueue = commandQueue.clone();

    while (historyQueue.size() > 0) {
      System.out.println(historyQueue.poll());

      if (++count % 5 == 0) {
        System.out.print(":");
        String str = keyboard.nextLine();
        if (str.equalsIgnoreCase("q"))
          break;
      }
    }
    // history2를 두 번 하면 원본 배열을 이미 꺼냈으므로 nullPointerException 실행 오류 발생
    // deletedNode.next = null; remove 후 first는 다음꺼로 바꼈지만 next(주소)값이 null이 되어
    // 찾아갈 수 없다. -> clone() 메서드의 deep copy 오버라이딩 필요
  }
}