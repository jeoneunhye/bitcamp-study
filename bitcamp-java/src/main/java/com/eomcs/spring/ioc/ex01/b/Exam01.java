// 스프링 IoC 컨테이너 사용
package com.eomcs.spring.ioc.ex01.b;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Exam01 {
  public static void main(String[] args) {
    // 2) 운영체제의 파일 시스템에서 설정 파일을 찾는 IoC 컨테이너
    // => 설정 파일 경로를 지정할 때 직접 파일 경로를 지정해야 한다.
    // => 주의!
    //    설정 파일 경로를 지정할 때 'URL 형식'으로 지정해야 한다.
    //    URL 형식에서는 파일 시스템을 가리킬 때 다음의 접두어를 붙인다.
    //    file://설정파일경로
    ApplicationContext iocContainer =
        new FileSystemXmlApplicationContext(
            "file:///Users\\user\\git\\bitcamp-study\\bitcamp-java\\src\\main\\java\\com\\eomcs\\spring\\ioc\\ex01\\b\\application-context.xml");
    // 설정파일이 자바 클래스 경로가 아닌 다른 폴더에 있다면
    // FileSystemXmlApplicationContext 클래스를 사용한다.
    // 실행하면 설정파일에 등록된 대로 객체를 생성한다.
    // 실제로 잘 사용하지 않는다.

    System.out.println("실행 완료!");
  }
}