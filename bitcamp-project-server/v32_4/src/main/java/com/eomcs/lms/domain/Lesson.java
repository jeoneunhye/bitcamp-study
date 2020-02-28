package com.eomcs.lms.domain;

import java.io.Serializable;
import java.sql.Date;

public class Lesson implements Serializable {
  private static final long serialVersionUID = 20200131L;

  private int no;
  private String title;
  private String description;
  private Date startDate;
  private Date endDate;
  private int totalHours;
  private int dayHours;

  public static Lesson valueOf(String csv) {
    String[] data = csv.split(",");

    Lesson lesson = new Lesson();
    lesson.setNo(Integer.parseInt(data[0]));
    lesson.setTitle(data[1]);
    lesson.setDescription(data[2]);
    lesson.setStartDate(Date.valueOf(data[3]));
    lesson.setEndDate(Date.valueOf(data[4]));
    lesson.setTotalHours(Integer.parseInt(data[5]));
    lesson.setDayHours(Integer.parseInt(data[6]));

    return lesson;
  }

  public String toCsvString() {
    // 수업 데이터를 꺼내 CSV 형식의 문자열로 만들어 리턴한다.
    return String.format("%d,%s,%s,%s,%s,%d,%d", this.getNo(), this.getTitle(),
        this.getDescription(), this.getStartDate(), this.getEndDate(), this.getTotalHours(),
        this.getDayHours());
  }

  @Override
  public boolean equals(Object obj) {
    if (obj.getClass() != Lesson.class)
      return false;

    Lesson other = (Lesson) obj;

    if (this.no != other.no)
      return false;

    if (!this.title.equals(other.title))
      return false;

    if (!this.description.equals(other.description))
      return false;

    if (this.startDate.compareTo(other.startDate) != 0)
      return false;

    if (this.endDate.compareTo(other.endDate) != 0)
      return false;

    if (this.totalHours != other.totalHours)
      return false;

    if (this.dayHours != other.dayHours)
      return false;

    return true;
  }


  public void setNo(int no) {
    this.no = no;
  }
  public int getNo() {
    return this.no;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getTitle() {
    return title;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public String getDescription() {
    return description;
  }
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
  public Date getStartDate() {
    return startDate;
  }
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
  public Date getEndDate() {
    return endDate;
  }
  public void setTotalHours(int totalHours) {
    this.totalHours = totalHours;
  }
  public int getTotalHours() {
    return totalHours;
  }
  public void setDayHours(int dayHours) {
    this.dayHours = dayHours;
  }
  public int getDayHours() {
    return dayHours;
  }
}