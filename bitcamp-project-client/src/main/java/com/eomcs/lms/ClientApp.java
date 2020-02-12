// LMS 클라이언트
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import com.eomcs.lms.handler.BoardAddCommand;
import com.eomcs.lms.handler.BoardDeleteCommand;
import com.eomcs.lms.handler.BoardDetailCommand;
import com.eomcs.lms.handler.BoardListCommand;
import com.eomcs.lms.handler.BoardUpdateCommand;
import com.eomcs.lms.handler.Command;
import com.eomcs.lms.handler.LessonAddCommand;
import com.eomcs.lms.handler.LessonDeleteCommand;
import com.eomcs.lms.handler.LessonDetailCommand;
import com.eomcs.lms.handler.LessonListCommand;
import com.eomcs.lms.handler.LessonUpdateCommand;
import com.eomcs.lms.handler.MemberAddCommand;
import com.eomcs.lms.handler.MemberDeleteCommand;
import com.eomcs.lms.handler.MemberDetailCommand;
import com.eomcs.lms.handler.MemberListCommand;
import com.eomcs.lms.handler.MemberUpdateCommand;
import com.eomcs.util.Prompt;

public class ClientApp {
  Scanner keyboard = new Scanner(System.in);
  Prompt prompt = new Prompt(keyboard);

  public void service() {
    String serverAddr = null;
    int port = 0;

    // 연결할 서버의 주소와 포트번호를 입력받기
    // prompt 객체 사용할 것
    // Scanner keyScan = new Scanner(System.in);

    try {
      //System.out.print("서버? ");
      // serverAddr = keyScan.nextLine();
      serverAddr = prompt.inputString("서버? ");

      // System.out.print("포트? ");
      // port = Integer.parseInt(keyScan.nextLine());
      port = prompt.inputInt("포트? ");

    } catch (Exception e) {
      System.out.println("서버 주소 또는 포트 번호가 유효하지 않습니다.");
      keyboard.close();
      return;
    }

    // 서버와 연결
    try(
        Socket socket = new Socket(serverAddr, port);

        //PrintStream out = new PrintStream(socket.getOutputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        // Scanner in = new Scanner(socket.getInputStream())
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        // 데이터를 통째로 입력받도록 Decorator 교체
        ) {
      System.out.println("서버와 연결되었음!");

      processCommand(out, in);

      System.out.println("서버와 연결을 정상적으로 끊음!");

    } catch (Exception e) {
      System.out.println("예외 발생:");
      e.printStackTrace();
    }

    // keyScan.close();
    keyboard.close();
  } // service() 끝

  // 명령 처리 메서드 생성, 내부적으로 호출(private)
  private void processCommand(ObjectOutputStream out, ObjectInputStream in) {
    // 서버에 보낼 문자 out, 서버로부터 받을 Object 객체 in
    Deque<String> commandStack = new ArrayDeque<>();
    Queue<String> commandQueue = new LinkedList<>();

    HashMap<String, Command> commandMap = new HashMap<>();
    commandMap.put("/board/add", new BoardAddCommand(out, in, prompt));
    commandMap.put("/board/list", new BoardListCommand(out, in));
    commandMap.put("/board/detail", new BoardDetailCommand(out, in, prompt));
    commandMap.put("/board/update", new BoardUpdateCommand(out, in, prompt));
    commandMap.put("/board/delete", new BoardDeleteCommand(out, in, prompt));

    commandMap.put("/member/list", new MemberListCommand(out, in));
    commandMap.put("/member/add", new MemberAddCommand(out, in, prompt));
    commandMap.put("/member/detail", new MemberDetailCommand(out, in, prompt));
    commandMap.put("/member/update", new MemberUpdateCommand(out, in, prompt));
    commandMap.put("/member/delete", new MemberDeleteCommand(out, in, prompt));

    commandMap.put("/lesson/list", new LessonListCommand(out, in));
    commandMap.put("/lesson/add", new LessonAddCommand(out, in, prompt));
    commandMap.put("/lesson/detail", new LessonDetailCommand(out, in, prompt));
    commandMap.put("/lesson/update", new LessonUpdateCommand(out, in, prompt));
    commandMap.put("/lesson/delete", new LessonDeleteCommand(out, in, prompt));

    try {
      while (true) {
        String command;
        command = prompt.inputString("\n명령> ");

        if (command.length() == 0)
          continue;

        if (command.equals("quit") || command.equals("/server/stop")) {
          out.writeUTF(command);
          out.flush();
          System.out.println("서버: " + in.readUTF());
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
    } catch (Exception e) {
      System.out.println("프로그램 실행 중 오류 발생!");
    }
    keyboard.close();
  }

  private void printCommandHistory(Iterator<String> iterator) {
    int count = 0;

    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      count++;

      if (count % 5 == 0) {
        String str = prompt.inputString(":");
        if (str.equalsIgnoreCase("q"))
          break;
      }
    }
  }

  public static void main(String[] args) {
    System.out.println("클라이언트 수업 관리 시스템입니다.");

    ClientApp clientApp = new ClientApp();
    clientApp.service();
  }
}