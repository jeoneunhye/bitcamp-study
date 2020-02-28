package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.LessonObjectFileDao;
import com.eomcs.lms.domain.Lesson;

public class LessonDetailServlet implements Servlet {
  // List<Lesson> lessons;
  LessonObjectFileDao lessonDao;

  public LessonDetailServlet(/*List<Lesson> lessons*/LessonObjectFileDao lessonDao) {
    // this.lessons = lessons;
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    // 입력받은 번호와 일치하는 lesson 객체의 존재 여부를 list에서 검사하고
    // 있으면 그 lesson 객체를 리턴하는 코드 LessonObjectFileDao.findByNo(int)로 이동
    /*
    Lesson lesson = null;
    for (Lesson l : lessons) {
      if (l.getNo() == no) {
        lesson = l;
        break;
      }
    }

    if (lesson != null) {
      out.writeUTF("OK");
      out.writeObject(lesson);

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 수업이 없습니다.");
    }
     */

    Lesson lesson = lessonDao.findByNo(no);// LessonObjectFileDao.findByNo(int);

    if (lesson != null) {
      out.writeUTF("OK");
      out.writeObject(lesson);
      // 해당 번호의 lesson 객체 데이터를 클라이언트쪽에서 꺼내 조회하기 위해 출력 필요!

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 수업이 없습니다.");
    }
  }
}