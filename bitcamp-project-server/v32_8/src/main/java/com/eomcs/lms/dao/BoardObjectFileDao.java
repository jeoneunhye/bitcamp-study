package com.eomcs.lms.dao;
// 파일로 Board 데이터를 읽고 쓰는 기능을 하는 클래스
import java.util.List;
import com.eomcs.lms.domain.Board;

public class BoardObjectFileDao extends AbstractObjectFileDao<Board> {
  // 공통 필드와 메서드 AbstractObjectFileDao 클래스로 이동
  // List<Lesson> list;
  // String filename;

  public BoardObjectFileDao(String filename) {
    // list = new ArrayList<>();
    // this.filename = filename;

    // loadData(); => 수퍼 클래스에서 메서드 호출

    super(filename);
  }

  // loadData() {}, saveData() {} 삭제 => 수퍼 클래스에서 메서드 구현

  // 서블릿 객체들이 데이터를 다룰 때 사용할 메서드를 정의한다.
  public int insert(Board board) throws Exception {
    if (indexOf(board.getNo()) > -1) {
      return 0;
    }

    list.add(board);
    saveData();
    System.out.println("데이터를 저장합니다.");

    return 1;
  }

  public List<Board> findAll() throws Exception {
    return list;
  }

  public Board findByNo(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return null;
    }

    return list.get(index);
  }

  public int update(Board board) throws Exception {
    int index = indexOf(board.getNo());
    if (index == -1) {
      return 0;
    }

    list.set(index, board);
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
  protected/*private*/ <K> int indexOf(K key/*int no*/) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == (int) key/*no*/) { // key가 어떤 타입인지 형변환 필요
        return i;
      }
    }

    return -1;
  }
}