// dynamic(동적) sql 다루기 - <bind> 사용법
package com.eomcs.mybatis.ex03;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0280 {
  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(
        "com/eomcs/mybatis/ex03/mybatis-config.xml");

    SqlSessionFactory factory =
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // 실행 예:
    // => 단어들을 포함하는 제목의 게시물을 검색

    HashMap<String,Object> params = new HashMap<>();

    Scanner keyScan = new Scanner(System.in);

    System.out.print("제목? ");
    String value = keyScan.nextLine();

    params.put("title", value);
    // <bind> 태그를 사용하면
    // like 문의 문자열 패턴을 만들 때 편하다.
    keyScan.close();

    List<Board> list = sqlSession.selectList("BoardMapper.select26", params);
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