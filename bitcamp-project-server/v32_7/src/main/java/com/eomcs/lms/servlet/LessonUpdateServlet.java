package com.eomcs.lms.servlet;
// serverApp에서
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.LessonObjectFileDao;
import com.eomcs.lms.domain.Lesson;

public class LessonUpdateServlet implements Servlet {
  // List<Lesson> lessons;
  LessonObjectFileDao lessonDao;

  public LessonUpdateServlet(/*List<Lesson> lessons*/LessonObjectFileDao lessonDao) {
    // this.lessons = lessons;
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Lesson lesson = (Lesson) in.readObject();

    // 데이터 중복 검사, list의 index번 lesson 객체를 변경하는 코드
    // LessonObjectFileDao.update(Lesson)로 이동
    /*
    int index = -1;
    for (int i = 0; i < lessons.size(); i++) {
      if (lessons.get(i).getNo() == lesson.getNo()) {
        index = i;
        break;
      }
    }

    if (index != -1) {
      lessons.set(index, lesson);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 수업이 없습니다.");
    }
     */

    if (lessonDao.update(lesson) > 0) {
      // update(Lesson); list의 index번 lesson 객체를 변경했으면 1을 리턴
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 수업이 없습니다.");
    }
  }
}