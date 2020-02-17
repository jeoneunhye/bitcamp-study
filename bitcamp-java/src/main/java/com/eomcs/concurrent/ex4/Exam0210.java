// 스레드의 생명주기(lifecycle) - 우선 순위 조회
package com.eomcs.concurrent.ex4;

public class Exam0210 {
  public static void main(String[] args) throws Exception {
    // 스레드의 우선 순위 범위
    // JVM은 우선 순위를 크게 10단계로 구분한다.
    System.out.printf("우선 순위 범위: %d ~ %d\n",
        Thread.MIN_PRIORITY, Thread.MAX_PRIORITY);

    System.out.printf("우선 순위 기본값: %d\n",
        Thread.NORM_PRIORITY);

    // "main" 스레드의 우선 순위 조회
    System.out.printf("main 스레드 우선 순위: %d\n",
        Thread.currentThread().getPriority());

    class MyThread extends Thread {
      public MyThread(String name) {
        super(name);
      }

      @Override
      public void run() {
        for (int i = 0; i < 1000; i++)
          System.out.printf("%s %d\n", this.getName(), i);
      }
    }

    MyThread t1 = new MyThread("t1");

    // "t1" 스레드의 우선 순위 조회
    // => "main" 스레드를 실행하는 동안 만든 스레드는 "main"의 자식 스레드라 부른다.
    // => 자식 스레드는 부모 스레드의 우선 순위와 같은 값을 갖는다.
    //    그래서 "t1" 스레드는 "main"의 우선 순위 값과 같다.
    System.out.printf("%s 스레드 우선 순위: %d\n",
        t1.getName(), t1.getPriority());

    // 우선 순위가 높으면 CPU 사용 배분을 좀 더 자주 받는다.
    // 스레드는 JVM에서 관리하는 것이 아니라 OS가 관리한다.
    // 즉 OS의 스레드를 이용하는 것이다.
    // 따라서 우선 순위에 따라 실행 스케줄을 어떻게 관리할 지는 OS에 따라 다르다.
    // Windows OS는 우선 순위를 크게 고려하지 않아서
    // Windows에서 실행할 때는 우선 순위에 영향을 적게 받는다.
    // Unix, Linux 계열 OS에서 실행할 때는 우선 순위에 영향을 받는다.

    // 주의! Java의 캐치프레이즈가 "Write Once, Run Anywhere!"다.
    // 즉 OS에 상관없이 동일하게 동작하게 만드는 것이 자바의 목적이다.
    // 그런데 OS에 따라 우선 순위에 차이가 난다면 실행시간도 달라지니
    // 자바의 목적에 부합하는 것이 아니다.
    // 그래서 가능한 OS에 영향을 덜 받는 방식으로 코딩해야 한다.
    // 이런 이유로 스레드를 다룰 때 우선 순위를 고려하는 방식으로 프로그래밍하지 말라!
  }
}