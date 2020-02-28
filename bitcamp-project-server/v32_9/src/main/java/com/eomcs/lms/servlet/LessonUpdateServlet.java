package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.json.LessonJsonFileDao;
import com.eomcs.lms.domain.Lesson;

public class LessonUpdateServlet implements Servlet {
  // json파일을 다루도록 객체를 변경 LessonObjectFileDao -> LessonJsonFileDao
  LessonJsonFileDao lessonDao;

  public LessonUpdateServlet(LessonJsonFileDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Lesson lesson = (Lesson) in.readObject();

    if (lessonDao.update(lesson) > 0) {
      // update(Lesson); list의 index번 lesson 객체를 변경했으면 1을 리턴
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 수업이 없습니다.");
    }
  }
}