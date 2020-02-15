// 스레드 그룹에 소속된 스레드들
package com.eomcs.concurrent.ex2;

public class Exam0130 {
  public static void main(String[] args) {
    Thread main = Thread.currentThread();
    ThreadGroup mainGroup = main.getThreadGroup();

    // 스레드 그룹에 소속된 스레드 목록을 알고 싶다면?
    Thread[] arr = new Thread[100]; // 먼저 소속된 스레드들을 담을 레퍼런스 배열 준비
    int count = mainGroup.enumerate(arr, false); // 배열에 스레드를 담고 몇 개를 담았는지 리턴
    // 두 번째 파라미터 값을 false로 지정하면,
    // 하위 그룹에 소속된 스레드들은 제외한다.
    // 즉, 현재 그룹에 소속된 스레드 목록만 가져오라는 뜻!

    System.out.println("main 그룹에 소속된 스레드들:");
    for (int i = 0; i < count; i++)
      System.out.println("   => " + arr[i].getName());
  }
}

// JVM의 스레드 계층도:
// main(TG)
// => main(T)
// => 다른 스레드는 없다.