// dynamic(동적) sql 다루기 - <foreach> 사용법 III
package com.eomcs.mybatis.ex03;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0270 {
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

    ArrayList<Object> words = new ArrayList<>();

    System.out.print("검색? ");
    String[] values = keyScan.nextLine().split(" ");
    for (String value : values) {
      words.add(value.trim()); // trim: 문자열의 앞, 뒤 공백 제거
    }

    params.put("words", words);

    keyScan.close();

    List<Board> list = sqlSession.selectList("BoardMapper.select25", params);
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