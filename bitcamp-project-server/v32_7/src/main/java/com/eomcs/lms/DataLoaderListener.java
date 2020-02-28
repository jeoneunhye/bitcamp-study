package com.eomcs.lms;

import java.util.Map;
import com.eomcs.context.ApplicationContextListener;
import com.eomcs.lms.dao.BoardObjectFileDao;
import com.eomcs.lms.dao.LessonObjectFileDao;
import com.eomcs.lms.dao.MemberObjectFileDao;

public class DataLoaderListener implements ApplicationContextListener {
  // 데이터를 다루는 XxxObjectFileDao에서 List 객체를 준비한다.
  // List<Lesson> lessonList = new ArrayList<>();
  // List<Member> memberList = new ArrayList<>();
  // List<Board> boardList = new ArrayList<>();

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩합니다.");

    // XxxObjectFileDao의 구현 객체가 생성될 때 로딩되도록 변경
    /*
    loadLessonData();
    loadMemberData();
    loadBoardData();
     */

    // context.put("boardList", boardList);
    // context.put("lessonList", lessonList);
    // context.put("memberList", memberList);

    // 파일 로딩 및 저장 메서드, 데이터 처리에 필요한 List를 갖고 있다.
    LessonObjectFileDao lessonDao = new LessonObjectFileDao("./lesson.ser2");
    MemberObjectFileDao memberDao = new MemberObjectFileDao("./member.ser2");
    BoardObjectFileDao boardDao = new BoardObjectFileDao("./board.ser2");

    // contextInitialized(Map)를 호출한 쪽(serverApp)에서
    // DAO 객체를 사용할 수 있도록 Map 객체에 담아둔다.
    context.put("lessonDao", lessonDao);
    context.put("memberDao", memberDao);
    context.put("boardDao", boardDao);
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    // XxxObjectFileDao에서 insert, update, delete 메서드를 호출할 때(list의 데이터가 변경됐을 때)
    // 파일에 저장하도록 변경
    /*
    saveBoardData();
    saveLessonData();
    saveMemberData();
    
    System.out.println("데이터를 저장합니다.");
     */
  }

  // 데이터를 로딩하고 저장하는 메서드 => XxxObjectFileDao로 이동
  // loadLessonData(), saveLessonData(), loadMemberData(), saveMemberData(),
  // loadBoardData(), saveMemberData() 삭제
}