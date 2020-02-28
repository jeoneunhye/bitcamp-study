package com.eomcs.lms;

import java.util.Map;
import com.eomcs.context.ApplicationContextListener;
import com.eomcs.lms.dao.json.BoardJsonFileDao;
import com.eomcs.lms.dao.json.LessonJsonFileDao;
import com.eomcs.lms.dao.json.MemberJsonFileDao;

public class DataLoaderListener implements ApplicationContextListener {
  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩합니다.");

    // json파일을 다루도록 객체를 변경 XxxObjectFileDao -> XxxJsonFileDao
    LessonJsonFileDao lessonDao = new LessonJsonFileDao("./lesson.json");
    MemberJsonFileDao memberDao = new MemberJsonFileDao("./member.json");
    BoardJsonFileDao boardDao = new BoardJsonFileDao("./board.json");

    context.put("lessonDao", lessonDao);
    context.put("memberDao", memberDao);
    context.put("boardDao", boardDao);
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}
}