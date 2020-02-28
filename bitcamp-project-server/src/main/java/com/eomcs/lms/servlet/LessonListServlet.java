package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.LessonObjectFileDao;

public class LessonListServlet implements Servlet {
  // List<Lesson> lessons;
  LessonObjectFileDao lessonDao;

  public LessonListServlet(/*List<Lesson> lessons*/LessonObjectFileDao lessonDao) {
    // this.lessons = lessons;
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    // List<Lesson>를 리턴하는 코드 LessonObjectFileDao.findAll()로 이동
    /*
    out.writeUTF("OK");
    out.reset();
    out.writeObject(lessons);
     */

    out.writeUTF("OK");
    out.reset();
    out.writeObject(lessonDao.findAll()); // findAll(); List<Lesson>를 리턴
    // List의 데이터를 클라이언트쪽에서 꺼내 조회하기 위해 출력 필요!
  }
}