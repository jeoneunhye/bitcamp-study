// JDBC 드라이버 준비 - Driver 객체 생성 및 자동 등록
package com.eomcs.jdbc.ex1;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Exam0120 {
  public static void main(String[] args) throws Exception {
    // JDBC 드라이버 로딩 방법2: Driver 구현 객체 생성과 자동 등록
    // => Driver 객체를 생성하면 자동으로 DriverManager에 등록된다.
    // => 어떻게?
    // Driver 클래스가 로딩될 때 실행되는 static 블록에
    // Driver 인스턴스를 만들어 등록하는 코드가 있기 때문이다.
    try {
      // 1) Driver 구현체의 인스턴스를 생성한다.
      // => Driver 구현체가 로딩될 때 인스턴스가 생성되기 떄문에
      // 굳이 다음과 같이 인스턴스를 생성할 필요가 없다.
      new org.mariadb.jdbc.Driver();

      // 2) DriverManager에 구현체를 등록하는 코드가 없음!

      // 3) DriverManager에 자동 등록됐는지 확인해보자!
      java.sql.Driver driver = DriverManager.getDriver("jdbc:mariadb:");
      System.out.println(driver);
      // 해당 드라이버가 등록되지 않았으면 예외가 발생할 것이다.

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}