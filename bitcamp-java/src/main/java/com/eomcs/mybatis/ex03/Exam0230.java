// dynamic(동적) sql 다루기 - <set> 사용법
package com.eomcs.mybatis.ex03;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0230 {
  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(
        "com/eomcs/mybatis/ex03/mybatis-config.xml");

    SqlSessionFactory factory =
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // 실행 예:
    // => 게시글 변경하기
    // => 제목만 바꿀 경우, 내용만 바꿀 경우, 둘 다 바꿀 경우에 대해
    // 한 개의 SQL을 이용하여 처리할 수 있다.
    // <set>과 <if>를 이용하면 한 개의 SQL로 처리할 수 있다.

    HashMap<String,Object> params = new HashMap<>();

    Scanner keyScan = new Scanner(System.in);

    System.out.print("번호? ");
    String value = keyScan.nextLine();
    if (value.length() > 0) {
      params.put("no", value);
    }

    System.out.print("제목? ");
    value = keyScan.nextLine();
    if (value.length() > 0) {
      params.put("title", value);
    }

    System.out.print("내용? ");
    value = keyScan.nextLine();
    if (value.length() > 0) {
      params.put("content", value);
    }

    keyScan.close();

    int count = sqlSession.update("BoardMapper.update4", params);

    System.out.println(count + "개 변경!");

    // <if>
    // 이 태그를 이용하여 사용자가 입력한 항목만 변경한다.

    // <set>
    // 이 태그를 이용하여 SQL 앞, 뒤에 붙은 콤마(,)를 자동으로 제거한다.

    // => dynamic sql 제조 방법은 SQL 작성을

    // 실무
    // => 특정 컬럼의 값만 바꾸기 위해 여러 개의 SQL을 만드는 것은 번거롭다.
    // => 그래서 전체 컬럼의 값을 바꾸는 방식을 주로 사용한다.
    // => 문제점? 바꾸지 않아도 될 항목까지 변경하기 때문에 성능이 떨어진다.
    sqlSession.commit();

    sqlSession.close();
  }
}