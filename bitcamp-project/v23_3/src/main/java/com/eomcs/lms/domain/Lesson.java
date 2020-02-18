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
  
  // ctrl+space하면 목록 나옴
  // updateLesson() 메서드에서 변경값이 있을 때에만 true-> 수업 변경을 하도록 equals 메서드를 재정의
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