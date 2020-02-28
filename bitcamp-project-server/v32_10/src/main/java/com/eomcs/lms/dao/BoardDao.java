package com.eomcs.lms.dao;
// 데이터를 저장하고 꺼내는 방식(파일, 클라우드 저장소, DB 등)에 상관없이
// DAO 사용법을 통일하기 위해 메서드 호출 규칙을 정의한다.
import java.util.List;
import com.eomcs.lms.domain.Board;

public interface BoardDao {
  List<Board> findAll() throws Exception;

  Board findByNo(int no) throws Exception;

  int insert(Board board) throws Exception;

  int update(Board board) throws Exception;

  int delete(int no) throws Exception;
}