// 스레드의 생명주기(lifecycle) - running 상태 : CPU 쟁탈전(racing)
package com.eomcs.concurrent.ex4;

public class Exam0140 {
  public static void main(String[] args) throws Exception {
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

    MyThread t1 = new MyThread("홍길동 =====>");
    MyThread t2 = new MyThread("오호라 ------------>");
    MyThread t3 = new MyThread("우헤헤 ##");

    // 스레드를 시작시키는 순간 running 상태로 접어든다.
    // running 상태는 실행하고 있는 상태뿐만 아니라
    // CPU를 받을 수 있는 상태이기도 하다.
    // => CPU는 OS의 관리 정책에 따라 스레드나 프로세스에 배분된다.
    //    물론 OS가 CPU를 배분한 후 임의 시간 후에
    //    다다시 회수하여 다른 스레드(현재 스레드 포함)나 프로세스에 배분한다.
    //    때에 따라서 같은 스레드가 연속해서 배분받는 경우도 있을 것이다.

    t1.start();
    t2.start();
    t3.start();

    for (int i = 0; i < 1000; i++)
      System.out.printf("main 스레드: %d\n", i);
  }
}