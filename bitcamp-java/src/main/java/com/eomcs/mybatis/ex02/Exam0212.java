// Mybatis - SQL에 파라미터 지정하기: Map에 값을 담아 넘기기
package com.eomcs.mybatis.ex02;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0212 {
  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(
        "com/eomcs/mybatis/ex02/mybatis-config04.xml");

    SqlSessionFactory factory =
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // SQL을 실행할 때 파라미터 값을 전달하려면
    // 두 번째 파라미터로 전달해야 한다.
    // 여러 개의 값을 전달해야 한다면,
    // Map 객체에 담아 전달하라!
    // 또는 도메인 객체(ex: Board, Lesson 등)에 담아 전달하라!

    // ex) 페이징 처리를 위한 시작 인덱스와 갯수를 파라미터로 넘겨라.
    // Mapper select문의 parameterType을 map으로 설정한다.
    HashMap<String, Object> params = new HashMap<>();
    params.put("startIndex", 6);
    params.put("size", 3);
    // 6번 페이지부터 3개의 글 출력하기

    List<Board> list = sqlSession.selectList("BoardMapper.selectBoard3", params);

    for (Board board : list) {
      System.out.printf("%d, %s, %s, %s, %d\n",
          board.getNo(),
          board.getTitle(),
          board.getContent(),
          board.getRegisteredDate(),
          board.getViewCount());
    }

    sqlSession.close();
  }
}