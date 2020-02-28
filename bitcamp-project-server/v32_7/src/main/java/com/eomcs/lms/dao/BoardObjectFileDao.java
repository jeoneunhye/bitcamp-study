package com.eomcs.lms.dao;
// indexOf 메서드 사용 후
// 파일로 Board 데이터를 읽고 쓰는 기능을 하는 클래스
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.domain.Board;

public class BoardObjectFileDao {
  // 메모리에 임시 보관할 List 객체 준비
  List<Board> list;
  String filename;

  public BoardObjectFileDao(String filename) {
    list = new ArrayList<>();
    this.filename = filename;

    loadData();
  }

  // 데이터를 로딩하고 파일로 저장하는 메서드 DataLoaderListener 클래스에서 가져오기
  @SuppressWarnings("unchecked")
  public void loadData() { // loadBoardData -> loadData로 변경
    File file = new File(filename/*"./board.ser2"*/);
    // 파일을 나중에 쉽게 변경할 수 있도록 파일명을 BoardObjectFileDao 클래스의 생성자 파라미터로 받는다.

    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(file)))) {
      list = (List<Board>) in.readObject();

      System.out.printf("총 %d개의 게시물 데이터를 로딩했습니다.\n", list.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  public void saveData() { // saveBoardData -> saveData로 변경
    File file = new File(filename/*"./board.ser2"*/);

    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(file)))) {
      out.reset(); // 기존에 Serialize중에 cache(임시 보관)된 데이터를 초기화시킨 후
      out.writeObject(list); // 생성자 파라미터로 받은 list 객체를 새로 출력한다.

      System.out.printf("총 %d개의 게시물 데이터를 저장했습니다.\n", list.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생 - " + e.getMessage());
    }
  }

  // 서블릿 객체들이 데이터를 다룰 때, 사용할 메서드를 정의한다.
  public int/*void*/ insert(Board board) throws Exception {
    // 기존에 servlet에서 모두 수행하던 중복 데이터 검사 및 처리를
    // 중복 데이터 검사는 Dao, 파일에 데이터 처리는 Servlet이 하도록 코드 변경

    /*
    Board originBoard = findByNo(board.getNo());
    // 읽어온(저장할) Board 객체와 번호가 같은 기존 Board 객체가 있으면 originBoard에 넣는다.

    if (originBoard != null) { // 같은 번호의 게시물이 있다면
      return 0; // 저장 안 함
    }
     */

    if (indexOf(board.getNo()) > -1) {
      // -1보다 크다면 파라미터로 받은 Board의 번호와 일치하는 Board 객체가 list에 있다는 것이다.
      return 0; // 저장 안 함
    }

    list.add(board); // Board가 담긴 list에 파라미터로 받은 Board 객체 추가
    saveData(); // 변경된 list를 파일에 출력
    System.out.println("데이터를 저장합니다.");

    return 1; // 저장함
  }

  public List<Board> findAll() throws Exception {
    return list; // 생성자에서 만든 list를 리턴
  }

  public Board findByNo(int no) throws Exception {
    /*
    for (Board b : list) {
      if (b.getNo() == no) {
        return b;
      }
    }
     */

    int index = indexOf(no);
    if (index == -1) { // 해당하는 번호를 가진 Board 객체를 찾지 못 함
      return null;
    }

    return list.get(no);
  }

  public int/*void*/ update(Board board) throws Exception {
    /*
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == board.getNo()) {
        list.set(i, board);
        saveData();
        System.out.println("데이터를 저장합니다.");

        return 1;
      }
    }
    return 0;
     */

    int index = indexOf(board.getNo());
    if (index == -1) { // 해당하는 번호를 가진 Board 객체를 찾지 못 함
      return 0; // 변경 안 함
    }

    list.set(index, board); // 해당하는 list의 index번째 Board 객체를 파라미터로 받은 board로 교체한다.
    saveData(); // 변경된 list를 파일에 출력
    System.out.println("데이터를 저장합니다.");

    return 1; // 변경함
  }

  public int/*void*/ delete(int no) throws Exception {
    /*
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == no) {
        list.remove(i);
        saveData();
        System.out.println("데이터를 저장합니다.");

        return 1;
      }
    }
    return 0;
     */

    int index = indexOf(no);
    if (index == -1) { // 해당하는 번호를 가진 Board 객체를 찾지 못 함
      return 0; // 삭제 안 함
    }

    list.remove(index); // 해당하는 list의 index번째 Board 객체를 삭제한다.
    saveData(); // 변경된 list를 파일에 출력
    System.out.println("데이터를 저장합니다.");

    return 1; // 삭제함
  }

  // 내부적으로 사용 private
  private int indexOf(int no) {
    for (int i = 0; i < list.size(); i++) { // list에 담긴 Board의 갯수만큼 반복
      if (list.get(i).getNo() == no) {
        // 파라미터로 받은 번호와 일치하는 번호를 가진 Board 객체가 list의 i번째에 있다면
        return i; // 그 i를 리턴
      }
    }

    return -1; // 일치하는 번호를 가진 Board 객체가 없다면 -1을 리턴
  }
}