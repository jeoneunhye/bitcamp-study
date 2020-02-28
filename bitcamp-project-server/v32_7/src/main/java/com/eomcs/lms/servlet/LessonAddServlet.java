package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.LessonObjectFileDao;
import com.eomcs.lms.domain.Lesson;

public class LessonAddServlet implements Servlet {
  // List<Lesson> lessons;
  LessonObjectFileDao lessonDao;

  public LessonAddServlet(/*List<Lesson> lessons*/LessonObjectFileDao lessonDao) {
    // this.lessons = lessons;
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Lesson lesson = (Lesson) in.readObject();

    // 데이터 중복 검사, 입력받은 lesson 객체를 list에 추가하는 코드
    // LessonObjectFileDao.insert(Lesson)로 이동
    /*
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
     */

    if (lessonDao.insert(lesson) > 0) { // insert(Lesson); 저장했으면 1을 리턴
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 수업이 있습니다.");
    }
  }
}