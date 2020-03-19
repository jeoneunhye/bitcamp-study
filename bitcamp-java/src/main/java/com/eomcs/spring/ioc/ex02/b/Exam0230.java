// 객체 생성 - bean의 별명을 알아내기: name만 설정한 경우
package com.eomcs.spring.ioc.ex02.b;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.eomcs.spring.ioc.SpringUtils;

public class Exam0230 {
  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "com/eomcs/spring/ioc/ex02/b/application-context.xml");

    SpringUtils.printBeanList(iocContainer);

    System.out.println("[별명]");
    String[] aliases = iocContainer.getAliases("c4");
    for (String alias : aliases) {
      System.out.println(alias);
    }

    // => name만 설정한 경우 첫 번째 별명이 id로 사용된다.
    // 두 번째 별명이 없다면 별명이 없는 것이다.
  }
}