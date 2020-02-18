package com.eomcs.lms.domain;

import java.sql.Date;

public class Lesson {
  private int no;
  private String title;
  private String description;
  private Date startDate;
  private Date endDate;
  private int totalHours;
  private int dayHours;
  
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
