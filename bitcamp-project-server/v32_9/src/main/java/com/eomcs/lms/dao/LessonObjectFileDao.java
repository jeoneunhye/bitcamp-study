package com.eomcs.lms.dao;
// 파일의 Lesson 데이터를 읽고 쓰는 기능을 하는 클래스
import java.util.List;
import com.eomcs.lms.domain.Lesson;

public class LessonObjectFileDao extends AbstractObjectFileDao<Lesson> {
  public LessonObjectFileDao(String filename) {
    super(filename);
  }

  // 서블릿 객체들이 데이터를 다룰 때 사용할 메서드를 정의한다.
  public int insert(Lesson lesson) throws Exception {
    if (indexOf(lesson.getNo()) > -1) {
      return 0;
    }

    list.add(lesson);
    saveData();

    return 1;
  }

  public List<Lesson> findAll() throws Exception {
    return list;
  }

  public Lesson findByNo(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return null;
    }

    return list.get(no);
  }

  public int update(Lesson lesson) throws Exception {
    int index = indexOf(lesson.getNo());
    if (index == -1) {
      return 0;
    }

    list.set(index, lesson);
    saveData();

    return 1;
  }

  public int delete(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return 0;
    }

    list.remove(index);
    saveData();

    return 1;
  }

  @Override
  protected <K> int indexOf(K key) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == (int) key) { // key가 어떤 타입인지 형변환 필요
        return i;
      }
    }

    return -1;
  }
}