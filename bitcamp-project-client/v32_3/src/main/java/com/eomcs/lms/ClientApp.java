// LMS 클라이언트
package com.eomcs.lms;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import com.eomcs.lms.handler.Command;
import com.eomcs.util.Prompt;

public class ClientApp {
  Scanner keyboard = new Scanner(System.in);
  Prompt prompt = new Prompt(keyboard); // Prompt 객체 사용

  public void service() {
    // printCommandHistory()에서는 Stack과 Queue를 복제한 Iterator를
    // 사용하기 때문에 이 객체들을 메서드 안으로 가져온다.
    // 인스턴스 필드를 로컬 필드로 이동(리팩토링)
    Deque<String> commandStack = new ArrayDeque<>();
    Queue<String> commandQueue = new LinkedList<>();

    HashMap<String, Command> commandMap = new HashMap<>();
    String command;
    while (true) {
      // System.out.print("\n명령> ");
      // command = keyboard.nextLine();
      command = prompt.inputString("\n명령> ");

      if (command.length() == 0)
        continue;

      if (command.equals("quit")) {
        System.out.println("안녕!");
        break;
        
      } else if (command.equals("history")) {
        printCommandHistory(commandStack.iterator());
        continue;
        
      } else if (command.equals("history2")) {
        printCommandHistory(commandQueue.iterator());
        continue;
      }

      commandStack.push(command);
      commandQueue.offer(command);

      Command commandHandler = commandMap.get(command);

      if (commandHandler != null) {
        try {
          commandHandler.execute();
          
        } catch (Exception e) {
          System.out.printf("명령어 실행 중 오류 발생: %s\n", e.getMessage());
        }
        
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
    }
    
    keyboard.close();
  }

  private void printCommandHistory(Iterator<String> iterator) {
    int count = 0;

    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      count++;

      if (count % 5 == 0) {
        // System.out.print(":");
        // String str = keyboard.nextLine();
        String str = prompt.inputString(":");
        if (str.equalsIgnoreCase("q"))
          break;
      }
    }
  }

  public static void main(String[] args) {
    System.out.println("클라이언트 수업 관리 시스템입니다.");

    ClientApp clientApp = new ClientApp();
    clientApp.service(); // 클라이언트의 요청
    
    /*
    String serverAddr = null;
    int port = 0;
    Scanner keyScan = new Scanner(System.in);
    
    try {
      System.out.print("서버? ");
      serverAddr = keyScan.nextLine();
      System.out.print("포트? ");
      port = Integer.parseInt(keyScan.nextLine());
      
    } catch (Exception e) {
      System.out.println("서버 주소 또는 포트 번호가 유효하지 않습니다.");
      keyScan.close();
      return;
    }
    
    try(
        Socket socket = new Socket(serverAddr, port);
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream())
        ) {
      System.out.println("서버와 연결되었음!");
      
      System.out.print("서버에 전송할 메시지를 입력하세요: ");
      String sendMsg = keyScan.nextLine();
      
      out.println(sendMsg);
      System.out.println("서버에 메시지를 전송하였음!");
      
      String message = in.nextLine();
      System.out.println("서버로부터 메시지를 수신하였음!");
      System.out.println("서버가 보낸 메시지: " + message);
      
      System.out.println("서버와 연결을 정상적으로 끊음!");
      
    } catch (Exception e) {
      System.out.println("예외 발생:");
      e.printStackTrace();
    }
    
    keyScan.close();
     */
  }
}