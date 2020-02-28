package com.eomcs.lms;

import java.util.Map;
import com.eomcs.context.ApplicationContextListener;
import com.eomcs.lms.dao.BoardObjectFileDao;
import com.eomcs.lms.dao.LessonObjectFileDao;
import com.eomcs.lms.dao.MemberObjectFileDao;

public class DataLoaderListener implements ApplicationContextListener {
  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩합니다.");

    LessonObjectFileDao lessonDao = new LessonObjectFileDao("./lesson.ser2");
    MemberObjectFileDao memberDao = new MemberObjectFileDao("./member.ser2");
    BoardObjectFileDao boardDao = new BoardObjectFileDao("./board.ser2");

    context.put("lessonDao", lessonDao);
    context.put("memberDao", memberDao);
    context.put("boardDao", boardDao);
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}
}