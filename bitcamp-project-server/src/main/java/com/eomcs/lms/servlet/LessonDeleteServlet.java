package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.LessonObjectFileDao;

public class LessonDeleteServlet implements Servlet {
  // List<Lesson> lessons;
  LessonObjectFileDao lessonDao;

  public LessonDeleteServlet(/*List<Lesson> lessons*/LessonObjectFileDao lessonDao) {
    // this.lessons = lessons;
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    // 데이터 중복 검사, list에서 해당 번호의 Lesson 객체를 삭제하는 코드
    // LessonObjectFileDao.delete(int)로 이동
    /*
    int index = -1;
    for (int i = 0; i < lessons.size(); i++) {
      if (lessons.get(i).getNo() == no) {
        index = i;
        break;
      }
    }

    if (index != -1) {
      lessons.remove(index);
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 수업이 없습니다.");
    }
     */

    if (lessonDao.delete(no) > 0) { // delete(int); 삭제했으면 1을 리턴
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 수업이 없습니다.");
    }
  }
}