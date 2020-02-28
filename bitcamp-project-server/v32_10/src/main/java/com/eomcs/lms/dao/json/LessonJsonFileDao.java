package com.eomcs.lms.dao.json;
// LessonDao 인터페이스를 구현
// Json 파일의 lesson 데이터를 읽고 쓰는 기능을 하는 클래스
import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonJsonFileDao extends AbstractJsonFileDao<Lesson> implements LessonDao {
  public LessonJsonFileDao(String filename) {
    super(filename);
  }

  // 서블릿 객체들이 데이터를 다룰 때 사용할 메서드를 정의한다.
  public int insert(Lesson lesson) throws Exception {
    if (indexOf(lesson.getNo()) > -1) {
      return 0;
    }

    list.add(lesson);
    saveData();
    System.out.println("데이터를 저장합니다.");

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

    return list.get(index);
  }

  public int update(Lesson lesson) throws Exception {
    int index = indexOf(lesson.getNo());
    if (index == -1) {
      return 0;
    }

    list.set(index, lesson);
    saveData();
    System.out.println("데이터를 저장합니다.");

    return 1;
  }

  public int delete(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return 0;
    }

    list.remove(index);
    saveData();
    System.out.println("데이터를 저장합니다.");

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