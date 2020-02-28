package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.eomcs.lms.domain.Lesson;

public class LessonListServlet implements Servlet {
  List<Lesson> lessons;

  // service()가 사용할 의존 객체를 생성자로부터 받아 온다.
  // serverApp.processRequest()에서 servletMap.get("/lesson/list");이 호출될 때
  // 이 클래스의 객체가 servlet 레퍼런스에 담긴다.
  public LessonListServlet(List<Lesson> lessons) {
    this.lessons = lessons;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(lessons);
  }
}