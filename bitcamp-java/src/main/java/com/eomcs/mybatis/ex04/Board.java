// DAO와 서비스 객체 사이에서 데이터를 실어나르는 용도로 사용한다.
// => DTO(Data Transfer Object)라 부른다.
// => "도메인 객체(domain)"라고도 부른다.
package com.eomcs.mybatis.ex04;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
// 외부 저장소로 객체를 내보낼 수 있도록 Serial 기능을 활성화시킨다.
public class Board implements Serializable {
  private static final long serialVersionUID = 1L;
  // DB 테이블의 컬럼 값을 저장할 인스턴스 변수를 준비한다.
  // => 보통 컬럼이름은 DB 관례에 따라 약자로 기술한다.
  // => 그러나 자바에서는 자바의 관례에 따라 변수명을 만들라!
  //    DB 컬럼명과 같게 하지 말라!
  int no;
  String title;
  String content;
  Date registeredDate;
  int viewCount;

  List<AttachFile> files;
  // 자바에서 Board 객체가 files 객체 필드를 선언하고 있는 경우
  // 1. Board ◆---> AttachFile composition
  // Board 객체가 삭제될 때 files 객체도 함께 제거된다.
  // 2. Board ◇---> AttachFile aggregation
  // Board 객체가 삭제된다고 해도 files 객체는 제거되지 않는다.

  // 개발하는 동안 객체의 값을 확인할 수 있도록 toString()을 오버라이딩한다.
  @Override
  public String toString() {
    return "Board [no=" + no + ", title=" + title + ", content=" + content + ", registeredDate="
        + registeredDate + ", viewCount=" + viewCount + "]";
  }

  // 셋터와 겟터 생성
  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public List<AttachFile> getFiles() {
    return files;
  }

  public void setFiles(List<AttachFile> files) {
    this.files = files;
  }
}