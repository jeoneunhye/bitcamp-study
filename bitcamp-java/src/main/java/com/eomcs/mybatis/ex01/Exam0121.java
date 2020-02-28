// Mybatis - 컬럼 이름과 프로퍼티 이름을 일치시키기
package com.eomcs.mybatis.ex01;

import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0121 {
  public static void main(String[] args) throws Exception {
    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build
        (Resources.getResourceAsStream("com/eomcs/mybatis/ex01/mybatis-config.xml"));

    SqlSession sqlSession = factory.openSession();

    List<Board> list = sqlSession.selectList("BoardMapper.selectBoard2");

    // 컬럼 이름과 자바 객체의 프로퍼티 이름을 같게 하기
    // -------------------------------------------------------------------
    // <select id="selectBoard" resultType="com.eomcs.mybatis.ex01.Board">
    // select
    //   board_id as no,
    //   title,
    //   contents as content,
    //   created_date as registeredDate,
    //   view_count as viewCount
    // from x_board
    // </select>
    // -------------------------------------------------------------------
    // 컬럼의 값을 자바 객체에 담으려면 컬럼과 같은 이름의 프로퍼티가 있어야 한다.
    // 없다면 위와 같이 프로퍼티명을 컬럼의 별명으로 지정하라.
    // 컬럼 이름과 일치하는 프로퍼티를 찾아 값을 입력한다.
    // board_id     ==> setBoard_id(컬럼값)
    // title        ==> setTitle(컬럼값)
    // contents     ==> setContents(컬럼값)
    // created_date ==> setCreated_date(컬럼값)
    // view_count   ==> setView_count(컬럼값)
    // 컬럼 이름과 일치하는 프로퍼티(setter)가 없다면
    // 그 컬럼의 값은 객체에 담을 수 없다.
    // => 이 예제에서 컬럼 이름과 일치하는 프로퍼티는 title밖에 없다.
    // Board 클래스를 확인해 보면 setContents는 있지만 setContents는 없다.
    // setNo는 있지만 setBoard_Id는 없다.

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