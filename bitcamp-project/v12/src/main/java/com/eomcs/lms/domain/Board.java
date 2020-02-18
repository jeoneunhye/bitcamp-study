package com.eomcs.lms.domain;

import java.sql.Date;
// 중복으로 사용되는 설계도=값 객체를 하나로 통합하여
// 별도 파일로 만듦 (유지보수가 쉽도록)
// 패키지명을 vo(value object) 또는 domain라고 이름 짓는다
public class Board {    // 모든 항목(변수)에 public을 추가
  public int no;
  public String title;
  public Date date;
  public int viewCount;
  public String writer;
}
