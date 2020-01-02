package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Lesson;

public class LessonList {
  private static final int DEFAULT_SIZE = 100;
  private int size = 0;
  private Lesson[] lessons;
  
  public LessonList() {
    this.lessons = new Lesson[DEFAULT_SIZE];
  }
  
  public LessonList(int capacity) {
    if (capacity > DEFAULT_SIZE &&
        capacity < 10000) {
      this.lessons = new Lesson[capacity];
    } else {
      this.lessons = new Lesson[DEFAULT_SIZE];
    }
  }
  
  public void add(Lesson lesson) {
    this.lessons[size++] = lesson;
  }
  
  public Lesson[] toArray() {
    Lesson[] arr = new Lesson[this.size];
    
    for (int i = 0; i < this.size; i++) {
      arr[i] = this.lessons[i];
    }
    return arr;
  }
}