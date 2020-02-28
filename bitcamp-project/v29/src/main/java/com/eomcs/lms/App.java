package com.eomcs.lms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.handler.BoardAddCommand;
import com.eomcs.lms.handler.BoardDeleteCommand;
import com.eomcs.lms.handler.BoardDetailCommand;
import com.eomcs.lms.handler.BoardListCommand;
import com.eomcs.lms.handler.BoardUpdateCommand;
import com.eomcs.lms.handler.Command;
import com.eomcs.lms.handler.ComputePlusCommand;
import com.eomcs.lms.handler.HelloCommand;
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

public class App {
  static Scanner keyboard = new Scanner(System.in);
  static Deque<String> commandStack = new ArrayDeque<>();
  static Queue<String> commandQueue = new LinkedList<>();

  static ArrayList<Lesson> lessonList = new ArrayList<>();
  static LinkedList<Member> memberList = new LinkedList<>();
  static LinkedList<Board> boardList = new LinkedList<>();

  public static void main(String[] args) {
    // 파일에서 데이터 로딩
    loadLessonData();
    loadMemberData();
    loadBoardData();

    Prompt prompt = new Prompt(keyboard);

    Map<String, Command> commandMap = new HashMap<>();

    commandMap.put("/lesson/add", new LessonAddCommand(prompt, lessonList));
    commandMap.put("/lesson/list", new LessonListCommand(lessonList));
    commandMap.put("/lesson/detail", new LessonDetailCommand(prompt, lessonList));
    commandMap.put("/lesson/update", new LessonUpdateCommand(prompt, lessonList));
    commandMap.put("/lesson/delete", new LessonDeleteCommand(prompt, lessonList));

    commandMap.put("/member/add", new MemberAddCommand(prompt, memberList));
    commandMap.put("/member/list", new MemberListCommand(memberList));
    commandMap.put("/member/detail", new MemberDetailCommand(prompt, memberList));
    commandMap.put("/member/update", new MemberUpdateCommand(prompt, memberList));
    commandMap.put("/member/delete", new MemberDeleteCommand(prompt, memberList));

    commandMap.put("/board/add", new BoardAddCommand(prompt, boardList));
    commandMap.put("/board/list", new BoardListCommand(boardList));
    commandMap.put("/board/detail", new BoardDetailCommand(prompt, boardList));
    commandMap.put("/board/update", new BoardUpdateCommand(prompt, boardList));
    commandMap.put("/board/delete", new BoardDeleteCommand(prompt, boardList));

    commandMap.put("/hello", new HelloCommand(prompt));
    commandMap.put("/compute/plus", new ComputePlusCommand(prompt));

    String command;

    while (true) {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();

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

    // 데이터를 파일에 저장
    saveLessonData();
    saveMemberData();
    saveBoardData();
  }

  private static void printCommandHistory(Iterator<String> iterator) {
    int count = 0;

    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      count++;

      if (count % 5 == 0) {
        System.out.print(":");
        String str = keyboard.nextLine();
        if (str.equalsIgnoreCase("q"))
          break;
      }
    }
  }

  // 바이너리 데이터를 다루는 .data 파일로 변경
  private static void loadLessonData() {
    File file = new File("./lesson.data");

    // 파일 로딩 객체 변경 FileReader -> FileInputStream으로 파일을 로딩
    // 버퍼 객체 변경 BufferedReader -> BufferedInputStream
    // Java primitive data type을 입력하는데 적합한 DataInputStream을 FilterStream으로 사용한다.
    try (DataInputStream in =
        new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {

      // lessonList.addAll(Arrays.asList(new Gson().fromJson(in, Lesson[].class)));

      int size = in.readInt(); // 먼저 파일에 저장된 lesson이 담긴 list 객체의 크기를 읽는다.

      // list<Lesson>의 size만큼 반복하면서 lesson 객체에 값을 담는다.
      for (int i = 0; i < size; i++) {
        Lesson lesson = new Lesson();
        lesson.setNo(in.readInt());
        lesson.setTitle(in.readUTF());
        lesson.setDescription(in.readUTF());
        lesson.setStartDate(Date.valueOf(in.readUTF()));
        lesson.setEndDate(Date.valueOf(in.readUTF()));
        lesson.setTotalHours(in.readInt());
        lesson.setDayHours(in.readInt());

        lessonList.add(lesson);
      }

      System.out.printf("총 %d개의 수업 데이터를 로딩했습니다.\n", lessonList.size());

    } catch (IOException e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  private static void saveLessonData() {
    File file = new File("./lesson.data");

    // 파일 로딩 객체 변경 FileWriter -> FileOutputStream으로 파일을 로딩
    // 버퍼 객체 변경 BufferedWriter -> BufferedOutputStream
    // Java primitive data type을 입력하는데 적합한 DataOutputStream을 FilterStream으로 사용한다.
    try (DataOutputStream out =
        new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {

      // out.write(new Gson().toJson(lessonList));

      out.writeInt(lessonList.size()); // 먼저 파일에 담을 list의 크기를 알려준다.

      // lesson 객체의 정보를 파일에 출력한다.
      // 날짜 데이터는 문자열로 변환하여 출력한다.
      for (Lesson lesson : lessonList) {
        out.writeInt(lesson.getNo());
        out.writeUTF(lesson.getTitle());
        out.writeUTF(lesson.getDescription());
        out.writeUTF(String.valueOf(lesson.getStartDate()));
        out.writeUTF(String.valueOf(lesson.getEndDate()));
        out.writeInt(lesson.getTotalHours());
        out.writeInt(lesson.getDayHours());
      }

      System.out.printf("총 %d개의 수업 데이터를 저장했습니다.\n", lessonList.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());

    }
  }

  // 바이너리 데이터를 다루는 .data 파일로 변경
  private static void loadMemberData() {
    File file = new File("./member.data");

    // 파일 로딩 객체 변경 FileReader -> FileInputStream으로 파일을 로딩
    // 버퍼 객체 변경 BufferedReader -> BufferedInputStream
    // Java primitive data type을 입력하는데 적합한 DataInputStream을 FilterStream으로 사용한다.
    try (DataInputStream in =
        new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {

      // memberList.addAll(Arrays.asList(new Gson().fromJson(in, Member[].class)));

      int size = in.readInt(); // 먼저 파일에 저장된 lesson이 담긴 list 객체의 크기를 읽는다.

      // list<Member>의 size만큼 반복하면서 member 객체에 값을 담는다.
      for (int i = 0; i < size; i++) {
        Member member = new Member();
        member.setNo(in.readInt());
        member.setName(in.readUTF());
        member.setEmail(in.readUTF());
        member.setPassword(in.readUTF());
        member.setPhoto(in.readUTF());
        member.setTel(in.readUTF());
        member.setRegisteredDate(Date.valueOf(in.readUTF()));

        memberList.add(member);
      }

      System.out.printf("총 %d개의 회원 데이터를 로딩했습니다.\n", memberList.size());

    } catch (IOException e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());

    }
  }

  private static void saveMemberData() {
    File file = new File("./member.data");

    // 파일 로딩 객체 변경 FileWriter -> FileOutputStream으로 파일을 로딩
    // 버퍼 객체 변경 BufferedWriter -> BufferedOutputStream
    // Java primitive data type을 입력하는데 적합한 DataOutputStream을 FilterStream으로 사용한다.
    try (DataOutputStream out =
        new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {

      // out.write(new Gson().toJson(memberList));

      out.writeInt(memberList.size()); // 먼저 파일에 담을 list의 크기를 알려준다.

      // member 객체의 정보를 파일에 출력한다.
      // 날짜 데이터는 문자열로 변환하여 출력한다.
      for (Member member : memberList) {
        out.writeInt(member.getNo());
        out.writeUTF(member.getName());
        out.writeUTF(member.getEmail());
        out.writeUTF(member.getPassword());
        out.writeUTF(member.getPhoto());
        out.writeUTF(member.getTel());
        out.writeUTF(String.valueOf(member.getRegisteredDate()));
      }

      System.out.printf("총 %d개의 회원 데이터를 저장했습니다.\n", memberList.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());

    }
  }

  // 바이너리 데이터를 다루는 .data 파일로 변경
  private static void loadBoardData() {
    File file = new File("./board.data");

    // 파일 로딩 객체 변경 FileReader -> FileInputStream으로 파일을 로딩
    // 버퍼 객체 변경 BufferedReader -> BufferedInputStream
    // Java primitive data type을 입력하는데 적합한 DataInputStream을 FilterStream으로 사용한다.
    try (DataInputStream in =
        new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {

      // boardList.addAll(Arrays.asList(new Gson().fromJson(in, Board[].class)));

      int size = in.readInt(); // 먼저 파일에 저장된 lesson이 담긴 list 객체의 크기를 읽는다.

      // list<Board>의 size만큼 반복하면서 board 객체에 값을 담는다.
      for (int i = 0; i < size; i++) {
        Board board = new Board();
        board.setNo(in.readInt());
        board.setTitle(in.readUTF());
        board.setDate(Date.valueOf(in.readUTF()));
        board.setViewCount(in.readInt());
        board.setWriter(in.readUTF());

        boardList.add(board);
      }

      System.out.printf("총 %d개의 게시물 데이터를 로딩했습니다.\n", boardList.size());

    } catch (IOException e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());

    }
  }

  private static void saveBoardData() {
    File file = new File("./board.data");

    // 파일 로딩 객체 변경 FileWriter -> FileOutputStream으로 파일을 로딩
    // 버퍼 객체 변경 BufferedWriter -> BufferedOutputStream
    // Java primitive data type을 입력하는데 적합한 DataOutputStream을 FilterStream으로 사용한다.
    try (DataOutputStream out =
        new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {

      // out.write(new Gson().toJson(boardList));

      out.writeInt(boardList.size()); // 먼저 파일에 담을 list의 크기를 알려준다.

      // board 객체의 정보를 파일에 출력한다.
      // 날짜 데이터는 문자열로 변환하여 출력한다.
      for (Board board : boardList) {
        out.writeInt(board.getNo());
        out.writeUTF(board.getTitle());
        out.writeUTF(String.valueOf(board.getDate()));
        out.writeInt(board.getViewCount());
        out.writeUTF(board.getWriter());
      }

      System.out.printf("총 %d개의 게시물 데이터를 저장했습니다.\n", boardList.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());

    }
  }
}