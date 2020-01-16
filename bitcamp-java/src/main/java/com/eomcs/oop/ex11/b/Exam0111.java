// 클래스의 종류 : 패키지 멤버 클래스 사용
package com.eomcs.oop.ex11.b;

import java.io.File;
// 물론 Exam0110 또한 패키지 멤버 클래스다.
public class Exam0111 {
  public static void main(String[] args) throws Exception {
    // 현재 폴더에 들어있는 하위 폴더 및 파일들의 이름을 출력하라!
    
    File dir = new File(".");
    
    JavaFilter javaFilter = new JavaFilter();
    // 패키지 멤버 클래스의 인스턴스를 생성한다.
    // JavaFilter 클래스는 com.eomcs.oop.ex11.a 패키지에 소속된 멤버 클래스다.
    // FilenameFilter 구현체다.
    // File.list()를 호출할 때 라파미터로 넘겨준다.
    // 그러면 list() 메서드가 리턴할 이름을 준비할 때 Filter의 결정에 따라
    // 리턴 목록에 포함시킬지 말지 결정한다.

    String[] names = dir.list(javaFilter); // fileNameFilter를 구현한 javaFilter 클래스
    for (String name : names) {
      System.out.println(name);
    }
  }
}