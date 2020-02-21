// JDBC 프로그래밍 - DBMS에 SQL문 보내기 : update
package com.eomcs.jdbc.ex1;

import java.sql.DriverManager;

public class Exam0350 {
  public static void main(String[] args) throws Exception {
    try (
        java.sql.Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        java.sql.Statement stmt = con.createStatement();
        ) {

      // executeUpdate()
      // => DBMS 서버에 update문을 보낸다.
      // => 리턴값: 변경된 레코드의 갯수다.
      int count = stmt.executeUpdate(
          "update x_board set view_count = view_count + 20" // 기존 count에 20을 더하도록 update
          + " where board_id > 1");
      System.out.printf("%d개 변경 성공!", count);
    }
  }
}