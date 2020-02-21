// JDBC 프로그래밍 - DBMS에 SQL문 보내기 : insert
package com.eomcs.jdbc.ex1;

import java.sql.DriverManager;

public class Exam0310 {
  public static void main(String[] args) throws Exception {
    try (
        java.sql.Connection con = DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/studydb?user=study&password=1111");

        // java.sql.Statement 구현 객체를 얻는다.
        // - SQL문을 DBMS의 형식에 따라 인코딩하여 서버에 전달하는 일을 하는 객체
        java.sql.Statement stmt = con.createStatement()) {

      // MariaDB의 Connection 객체가 리턴하는 Statement 구현체의 클래스 이름은?
      System.out.println(stmt.getClass().getName()); // org.mariadb.jdbc.MariaDbStatement

      // Statement 객체 사용법:
      // 1) INSERT/UPDATE/DELETE 등 DML 관련 SQL문 전송
      // => executeUpdate()
      // => 리턴값: 변경(insert/update/delete)된 데이터의 갯수

      // 2) SELECT 등 DQL 관련 SQL문 전송
      // => executeQuery()
      // => 리턴값: 서버에서 데이터를 가져오는 일을 할 객체

      // 용어 정리
      // "DML(Data Manipulation Language)"
      // => insert, update, delete처럼 데이터를 조작하는 sql 명령을 말한다.
      // "DQL(Data Query Language)"
      // => select처럼 data를 조회하는 sql 명령을 말한다.
      // Oracle은 두 명령을 구분하지만 mySQL, mariaDB는 구분하지 않고 DML로 친다.
      int count = stmt.executeUpdate(
          "insert into x_board(title,contents) values('제목10','내용')"); // 세미콜론(;) 안 적는다.
      System.out.printf("%d개 입력 성공!", count); // count: insert한 갯수를 리턴받음
    }
  }
}