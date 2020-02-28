package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.json.LessonJsonFileDao;
import com.eomcs.lms.domain.Lesson;

public class LessonDetailServlet implements Servlet {
  // json파일을 다루도록 객체를 변경 LessonObjectFileDao -> LessonJsonFileDao
  LessonJsonFileDao lessonDao;

  public LessonDetailServlet(LessonJsonFileDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    Lesson lesson = lessonDao.findByNo(no); // LessonObjectFileDao.findByNo(int);

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