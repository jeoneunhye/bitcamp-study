package com.eomcs.lms;
// 애플리케이션이 시작되거나 종료될 때
// 데이터를 로딩하고 저장하는 기능을 한다.
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.eomcs.context.ApplicationContextListener;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;

public class DataLoaderListener implements ApplicationContextListener {
  List<Lesson> lessonList = new ArrayList<>();
  List<Member> memberList = new LinkedList<>();
  List<Board> boardList = new LinkedList<>();

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩합니다.");

    loadBoardData();
    loadLessonData();
    loadMemberData();

    context.put("lessonList", lessonList);
    context.put("memberList", memberList);
    context.put("boardList", boardList);
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("데이터를 저장합니다.");

    saveBoardData();
    saveLessonData();
    saveMemberData();
  }

  @SuppressWarnings("unchecked")
  public void loadLessonData() {
    File file = new File("./lesson.ser2");

    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(file)))) {
      lessonList = (List<Lesson>) in.readObject();

      System.out.printf("총 %d개의 수업 데이터를 로딩했습니다.\n", lessonList.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  public void saveLessonData() {
    File file = new File("./lesson.ser2");

    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(lessonList);

      System.out.printf("총 %d개의 수업 데이터를 저장했습니다.\n", lessonList.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생 - " + e.getMessage());

    }
  }

  @SuppressWarnings("unchecked")
  public void loadMemberData() {
    File file = new File("./member.ser2");

    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(file)))) {
      memberList = (List<Member>) in.readObject();

      System.out.printf("총 %d개의 회원 데이터를 로딩했습니다.\n", memberList.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  public void saveMemberData() {
    File file = new File("./member.ser2");

    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(memberList);

      System.out.printf("총 %d개의 회원 데이터를 저장했습니다.\n", memberList.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생 - " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  public void loadBoardData() {
    File file = new File("./board.ser2");

    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(file)))) {
      boardList = (List<Board>) in.readObject();

      System.out.printf("총 %d개의 게시글 데이터를 로딩했습니다.\n", boardList.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  public void saveBoardData() {
    File file = new File("./board.ser2");

    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(boardList);

      System.out.printf("총 %d개의 게시글 데이터를 저장했습니다.\n",
          boardList.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생 - " + e.getMessage());
    }
  }
}