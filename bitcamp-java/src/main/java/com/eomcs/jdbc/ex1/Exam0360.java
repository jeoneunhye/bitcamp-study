// JDBC 프로그래밍 - DBMS에 SQL문 보내기 : delete
package com.eomcs.jdbc.ex1;

import java.sql.DriverManager;

public class Exam0360 {
  public static void main(String[] args) throws Exception {
    try (
        java.sql.Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        java.sql.Statement stmt = con.createStatement();
        ) {

      // executeUpdate()
      // => DBMS 서버에 delete문을 보낸다.
      // => 리턴값: 삭제된 레코드의 갯수다.
      int count = stmt.executeUpdate(
          "delete from x_board where board_id > 5");
      System.out.printf("%d개 삭제 성공!", count);
    }
  }
}