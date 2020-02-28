package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.json.LessonJsonFileDao;

public class LessonDeleteServlet implements Servlet {
  // json파일을 다루도록 객체를 변경 LessonObjectFileDao -> LessonJsonFileDao
  LessonJsonFileDao lessonDao;

  public LessonDeleteServlet(LessonJsonFileDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    if (lessonDao.delete(no) > 0) { // delete(int); 삭제했으면 1을 리턴
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 수업이 없습니다.");
    }
  }
}