package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.eomcs.lms.domain.Lesson;

public class LessonAddServlet implements Servlet {
  List<Lesson> lessons;

  // service()가 사용할 의존 객체를 생성자로부터 받아 온다.
  // serverApp.processRequest()에서 servletMap.get("/lesson/add");이 호출될 때
  // 이 클래스의 객체가 servlet 레퍼런스에 담긴다.
  public LessonAddServlet(List<Lesson> lessons) {
    this.lessons = lessons;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    // ServerApp에서 service()를 호출하면 예외를 받고, 그 예외를
    // 처리하는 try문을 썼기 때문에 이 메서드에서 예외를 처리할 필요 없다.
    // try {
    Lesson lesson = (Lesson) in.readObject();

    int i = 0;
    for (; i < lessons.size(); i++) {
      if (lessons.get(i).getNo() == lesson.getNo()) {
        break;
      }
    }

    if (i == lessons.size()) {
      lessons.add(lesson);
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 수업이 있습니다.");
    }

    // } catch (Exception e) {
    // out.writeUTF("FAIL");
    // out.writeUTF(e.getMessage());
    // }
  }
}