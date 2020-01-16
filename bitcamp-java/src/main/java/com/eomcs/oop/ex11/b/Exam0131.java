// 클래스의 종류 : non-static 중첩 클래스(nested class) 사용
package com.eomcs.oop.ex11.b;

import java.io.File;
import java.io.FilenameFilter;

public class Exam0131 {
  // non-static nested class = inner class
  // => 스태틱이 붙지 않은 중첩 클래스. 결국 인스턴스 멤버다.
  // => 이 클래스는 인스턴스가 있어야만 사용할 수 있다.
  // => 보통 인스턴스 멤버를 사용하는 중첩 클래스를 만들 때 inner class로 정의한다.
  // => 당연히 inner 클래스는 인스턴스 메서드에서 주로 사용할 것이다.

  // inner class = non-static nested class
  // 바깥 클래스의 인스턴스를 사용하는 경우 inner class로 정의하라.
  // 인스턴스 필드를 사용하는 메서드에 대해 인스턴스 메서드로 선언하는 것과 같다.
  class JavaFilter implements FilenameFilter {
    public boolean accept(File dir, String name) {
      if (name.endsWith(".java")) 
        return true; 
      return false; 
    }
  }

  public static void main(String[] args) throws Exception {
    File dir = new File(".");

    JavaFilter javaFilter;
    
    //javaFilter = new JavaFilter(); // 컴파일 오류!
    // static 메서드에서는 static이 안 붙은 인스턴스 멤버를 사용할 수 없다.
    
    // static 메서드에서 인스턴스 멤버를 사용하려면
    // 인스턴스를 생성해야만 한다.
    Exam0131 obj = new Exam0131();
    
    // 그리고 이 인스턴스를 가지고 인스턴스 필드/메서드/중첩 클래스를 사용할 수 있다.
    javaFilter = obj.new JavaFilter();
    
    String[] names = dir.list(javaFilter);
    
    for (String name : names) {
      System.out.println(name);
    }
  }
}