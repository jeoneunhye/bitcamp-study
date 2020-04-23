// 데이터 가져오기 - 조인을 사용하여 한 개의 객체에 담기
package com.eomcs.mybatis.ex04;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0120 {
  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(
        "com/eomcs/mybatis/ex04/mybatis-config.xml");

    SqlSessionFactory factory =
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    Board board = sqlSession.selectOne("BoardMapper2.selectBoardWithFile", 1);

    System.out.println("[게시글 조회]");
    System.out.printf("번호: %d\n", board.getNo());
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContent());
    System.out.printf("등록일: %s\n", board.getRegisteredDate());
    System.out.printf("조회수: %d\n", board.getViewCount());
    System.out.println();

    // 조인을 사용하면
    // 개시물 객체 안에 첨부 파일 객체가 들어 있다.
    // 그래서 따로 첨부 파일을 select할 필요가 없다.

    System.out.println("[첨부 파일]");
    for (AttachFile file : board.getFiles()) {
      System.out.printf("%d, %s\n", file.getNo(), file.getFilePath());
    }

    sqlSession.close();
  }
}