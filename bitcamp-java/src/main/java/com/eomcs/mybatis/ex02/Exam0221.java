// Mybatis - SQL에 파라미터 지정하기: ${}의 쓰임새
package com.eomcs.mybatis.ex02;

import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0221 {
  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(
        "com/eomcs/mybatis/ex02/mybatis-config05.xml");

    SqlSessionFactory factory =
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // ex) 파라미터로 컬럼 이름을 넘겨주면
    // 해당 컬럼의 값을 내림차순으로 정렬한다.
    List<Board> list = sqlSession.selectList("BoardMapper.selectBoard2", "title");
    // ${} 문법은 값을 그대로 SQL문에 삽입한다.
    // https://mybatis.org/mybatis-3/sqlmap-xml.html
    // 공격을 방지하기 위해 문법 사용을 비권장한다.

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