package com.eomcs.lms.servlet;
// 의존 객체를 LessonJsonFileDao 클래스 -> LessonDao 인터페이스로 변경
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonAddServlet implements Servlet {
  // 레퍼런스 타입으로 DAO 클래스를 구체적으로 지정하기보다는
  // 인터페이스를 지정함으로써
  // 향후 다른 구현체로 교체하기 쉽도록 한다.
  LessonDao lessonDao;

  public LessonAddServlet(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Lesson lesson = (Lesson) in.readObject();

    if (lessonDao.insert(lesson) > 0) {
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 수업이 있습니다.");
    }
  }
}