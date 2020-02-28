package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.LessonObjectFileDao;
import com.eomcs.lms.domain.Lesson;

public class LessonAddServlet implements Servlet {
  // json파일을 다루도록 객체를 변경 LessonObjectFileDao -> LessonJsonFileDao
  LessonJsonFileDao lessonDao;

  public LessonAddServlet(LessonJsonFileDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Lesson lesson = (Lesson) in.readObject();

    if (lessonDao.insert(lesson) > 0) { // insert(Lesson); 저장했으면 1을 리턴
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 수업이 있습니다.");
    }
  }
}