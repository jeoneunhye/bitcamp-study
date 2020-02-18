package com.eomcs.lms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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

  // 스태틱 메서드인 loadXxxData(), saveXxxData()에서 사용할 수 있도록
  // main() 밖으로 빼내어 스태틱 필드로 만든다.
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

  // 클래스 내부에서 사용 -> private 선언
  private static void loadLessonData() {
    // 데이터가 보관된 파일의 정보를 준비한다.
    File file = new File("./lesson.csv");

    // finally문에서 사용한 객체를 종료하기 위해 필드를 밖에서 선언한다.
    FileReader in = null;
    Scanner dataScan = null;

    try {
      // 파일을 읽을 때 사용할 도구를 준비한다.
      in = new FileReader(file);

      // .csv 파일의 데이터를 한 줄 단위로 문자열을 읽는 기능이 필요한데
      // FileReader에는 그런 기능이 없다. Scanner를 FileReader에 연결하여 사용한다. (Helper 객체)
      dataScan = new Scanner(in);

      int count = 0; // 데이터 로딩 갯수를 출력하기 위해 필드 선언

      while (true) {
        try {
          // 파일에서 한 줄을 읽는다.
          String line = dataScan.nextLine();

          // 한 줄을 콤마(,)를 기준으로 나누어 배열에 담는다.
          String[] data = line.split(",");

          // 한 줄에 들어 있던 데이터를 추출하여 Lesson 객체에 담는다.
          // => 데이터 순서: 번호,수업명,수업내용,시작일,종료일,총수업시간,일수업시간
          Lesson lesson = new Lesson();
          lesson.setNo(Integer.parseInt(data[0]));
          lesson.setTitle(data[1]);
          lesson.setDescription(data[2]);
          lesson.setStartDate(Date.valueOf(data[3]));
          lesson.setEndDate(Date.valueOf(data[4]));
          lesson.setTotalHours(Integer.parseInt(data[5]));
          lesson.setDayHours(Integer.parseInt(data[6]));

          // Lesson 객체를 Command가 다루는 list에 저장한다.
          lessonList.add(lesson);
          count++;

        } catch (Exception e) {
          break;
        }
      }

      System.out.printf("총 %d개의 수업 데이터를 로딩했습니다.\n", count);

      // 예외처리 문법 적용 -> 파일에서 데이터를 읽다가 오류가 발생하더라도
      // 시스템을 멈추지 않고 계속 실행한다.
    } catch (FileNotFoundException e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());

    } finally {
      // Exception이 발생했을 때 자원이 제대로 해제되지 않을 수 있다.
      // try와 catch의 결과에 상관없이 무조건 실행하는 부분이므로
      // 자원 해제 코드를 주로 finally에 구현한다.
      // 자원이 서로 연결된 경우에는 다른 자원을 이용하는 객체부터 닫는다.
      try {
        dataScan.close();

      } catch (Exception e) {
        // Scanner 객체 닫을 때 발생하는 예외 무시
        // 왜? 닫다가 발생한 오류는 특별히 처리할 게 없다.
      }

      try {
        in.close();

      } catch (Exception e) {
        // FileReader 객체 닫을 때 발생하는 예외 무시
      }
    }
  }

  private static void saveLessonData() {
    // 데이터가 보관된 파일의 정보를 준비한다.
    File file = new File("./lesson.csv");

    FileWriter out = null; // finally문에서 사용한 객체를 종료하기 위해 필드를 밖에서 선언한다.

    try {
      // 파일에 데이터를 저장할 때 사용할 도구를 준비한다.
      out = new FileWriter(file);

      int count = 0; // 데이터 저장 갯수를 출력하기 위한 필드

      for (Lesson lesson : lessonList) {
        // 수업 목록에서 수업 데이터를 꺼내 CSV 형식의 문자열로 만든다.
        String line = String.format("%d,%s,%s,%s,%s,%d,%d\n", lesson.getNo(), lesson.getTitle(),
            lesson.getDescription(), lesson.getStartDate(), lesson.getEndDate(),
            lesson.getTotalHours(), lesson.getDayHours());

        out.write(line); // 데이터 정보 한 줄을 받아 파일에 출력한다.
        count++;
      }

      System.out.printf("총 %d개의 수업 데이터를 저장했습니다.\n", count);

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());

    } finally {
      try {
        out.close();

      } catch (IOException e) {
        // FileWriter 닫을 때 발생하는 예외 무시
      }
    }
  }

  private static void loadMemberData() {
    // 데이터가 보관된 파일의 정보를 준비한다.
    File file = new File("./member.csv");

    // finally문에서 사용한 객체를 종료하기 위해 필드를 밖에서 선언한다.
    FileReader in = null;
    Scanner dataScan = null;

    try {
      // 파일을 읽을 때 사용할 도구를 준비한다.
      in = new FileReader(file);

      // .csv 파일의 데이터를 한 줄 단위로 문자열을 읽는 기능이 필요한데
      // FileReader에는 그런 기능이 없다. Scanner를 FileReader에 연결하여 사용한다. (Helper 객체)
      dataScan = new Scanner(in);

      int count = 0; // 데이터 로딩 갯수를 출력하기 위해 필드 선언

      while (true) {
        try {
          // 파일에서 한 줄을 읽는다.
          String line = dataScan.nextLine();

          // 한 줄을 콤마(,)를 기준으로 나누어 배열에 담는다.
          String[] data = line.split(",");

          // 한 줄에 들어 있던 데이터를 추출하여 Member 객체에 담는다.
          // => 데이터 순서: 번호,이름,이메일,암호,사진,전화,등록일
          Member member = new Member();
          member.setNo(Integer.parseInt(data[0]));
          member.setName(data[1]);
          member.setEmail(data[2]);
          member.setPassword(data[3]);
          member.setPhoto(data[4]);
          member.setTel(data[5]);
          member.setRegisteredDate(Date.valueOf(data[6]));

          // Member 객체를 Command가 다루는 list에 저장한다.
          memberList.add(member);
          count++;

        } catch (Exception e) {
          break;
        }
      }

      System.out.printf("총 %d개의 회원 데이터를 로딩했습니다.\n", count);

    } catch (FileNotFoundException e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());

    } finally {
      try {
        dataScan.close();

      } catch (Exception e) {
        // Scanner 객체 닫을 때 발생하는 예외 무시
      }

      try {
        in.close();

      } catch (Exception e) {
        // FileReader 객체 닫을 때 발생하는 예외 무시
      }
    }
  }

  private static void saveMemberData() {
    // 데이터가 보관된 파일의 정보를 준비한다.
    File file = new File("./member.csv");

    FileWriter out = null; // finally문에서 사용한 객체를 종료하기 위해 필드를 밖에서 선언한다.

    try {
      // 파일에 데이터를 출력할 때 사용할 도구를 준비한다.
      out = new FileWriter(file);

      int count = 0; // 데이터 저장 갯수를 출력하기 위한 필드

      for (Member member : memberList) {
        // 회원 목록에서 회원 데이터를 꺼내 CSV 형식의 문자열로 만든다.
        String line = String.format("%d,%s,%s,%s,%s,%s,%s\n",
            member.getNo(), member.getName(), member.getEmail(), member.getPassword(),
            member.getPhoto(), member.getTel(), member.getRegisteredDate());

        out.write(line); // 데이터 정보 한 줄을 받아 파일에 출력한다.
        count++;
      }

      System.out.printf("총 %d개의 회원 데이터를 저장했습니다.\n", count);

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());

    } finally {
      try {
        out.close();

      } catch (IOException e) {
        // FileWriter 객체 닫을 때 발생하는 예외 무시
      }
    }
  }

  private static void loadBoardData() {
    // 데이터가 보관된 파일의 정보를 준비한다.
    File file = new File("./board.csv");

    // finally문에서 사용한 객체를 종료하기 위해 필드를 밖에서 선언한다.
    FileReader in = null;
    Scanner dataScan = null;

    try {
      // 파일을 읽을 때 사용할 도구를 준비한다.
      in = new FileReader(file);

      // .csv 파일의 데이터를 한 줄 단위로 문자열을 읽는 기능이 필요한데
      // FileReader에는 그런 기능이 없다. Scanner를 FileReader에 연결하여 사용한다. (Helper 객체)
      dataScan = new Scanner(in);

      int count = 0; // 데이터 로딩 갯수를 출력하기 위해 필드 선언

      while (true) {
        try {
          // 파일에서 한 줄을 읽는다.
          String line = dataScan.nextLine();

          // 한 줄을 콤마(,)를 기준으로 나누어 배열에 담는다.
          String[] data = line.split(",");

          // 한 줄에 들어 있던 데이터를 추출하여 Board 객체에 담는다.
          // => 데이터 순서: 번호,내용,등록일,조회수,작성자
          Board board = new Board();
          board.setNo(Integer.parseInt(data[0]));
          board.setTitle(data[1]);
          board.setDate(Date.valueOf(data[2]));
          board.setViewCount(Integer.parseInt(data[3]));
          board.setWriter(data[4]);

          // Board 객체를 Command가 다루는 list에 저장한다.
          boardList.add(board);
          count++;

        } catch (Exception e) {
          break;
        }
      }

      System.out.printf("총 %d개의 게시물 데이터를 로딩했습니다.\n", count);

    } catch (FileNotFoundException e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());

    } finally {
      try {
        dataScan.close();

      } catch (Exception e) {
        // Scanner 객체 닫을 때 발생하는 예외 무시
      }

      try {
        in.close();

      } catch (Exception e) {
        // FileReader 객체 닫을 때 발생하는 예외 무시
      }
    }
  }

  private static void saveBoardData() {
    // 데이터가 보관된 파일의 정보를 준비한다.
    File file = new File("./board.csv");

    FileWriter out = null; // finally문에서 사용한 객체를 종료하기 위해 필드를 밖에서 선언한다.

    try {
      // 파일에 데이터를 출력할 때 사용할 도구를 준비한다.
      out = new FileWriter(file);

      int count = 0; // 데이터 저장 갯수를 출력하기 위한 필드

      for (Board board : boardList) {
        // 게시물 목록에서 게시물 데이터를 꺼내 CSV 형식의 문자열로 만든다.
        String line = String.format("%d,%s,%s,%d,%s\n",
            board.getNo(), board.getTitle(), board.getDate(),
            board.getViewCount(), board.getWriter());

        out.write(line); // 데이터 정보 한 줄을 받아 파일에 출력한다.
        count++;
      }

      System.out.printf("총 %d개의 게시물 데이터를 저장했습니다.\n", count);

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());

    } finally {
      try {
        out.close();

      } catch (IOException e) {
        // FileWriter 객체 닫을 때 발생하는 예외 무시
      }
    }
  }
}