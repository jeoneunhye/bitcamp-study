package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.json.LessonJsonFileDao;

public class LessonListServlet implements Servlet {
  // json파일을 다루도록 객체를 변경 LessonObjectFileDao -> LessonJsonFileDao
  LessonJsonFileDao lessonDao;

  public LessonListServlet(LessonJsonFileDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(lessonDao.findAll()); // findAll(); List<Lesson>를 리턴
    // List의 데이터를 클라이언트쪽에서 꺼내 조회하기 위해 출력 필요!
  }
}