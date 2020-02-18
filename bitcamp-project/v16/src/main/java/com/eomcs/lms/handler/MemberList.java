package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;

public class MemberList {
  private static final int DEFAULT_SIZE = 100;
  private int size = 0;
  private Member[] members;
  
  public MemberList() {
    this.members = new Member[DEFAULT_SIZE];
  }
  public MemberList(int capacity) {
    if (capacity > DEFAULT_SIZE &&
        capacity < 10000) {
      this.members = new Member[capacity];
    } else {
      this.members = new Member[DEFAULT_SIZE];
    }
  }
  
  public void add(Member member) {
    this.members[this.size++] = member;
  }
  
  public Member[] toArray() {
    Member[] arr = new Member[size];
    
    for (int i = 0; i < size; i++) {
      arr[i] = members[i];
    }
    return arr;
  }
}