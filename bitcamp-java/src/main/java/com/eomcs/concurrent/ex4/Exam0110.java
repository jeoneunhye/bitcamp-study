// 스레드의 생명주기(lifecycle)
package com.eomcs.concurrent.ex4;

public class Exam0110 {
  public static void main(String[] args) {
    // 스레드의 생명주기
    // new Thread()    start()                 sleep()/wait()
    //     준비 ------------------> Running ------------------> Not Runnable
    //                               ^  |   <------------------
    //                               |  |    timeout()/notify()
    //                               X  |
    //                               |  |  run() 메서드 종료
    //                               |  V
    //                               Dead

    // Running 상태? CPU만 주어지면 그 프로그램을 실행할 수 있는 상태
    // - CPU를 받아서 실행 중이거나 CPU를 받을 수 있는 상태
    // Not Runnable 상태?
    // - CPU를 받지 않는 상태

    // run() 메서드 종료 후 다시 running 상태로 돌아갈 수 없다.
    // => 새로 스레드를 만들어 실행하는 방법 밖에 없다!
    System.out.println("스레드 실행 전");

    new Thread() {
      @Override
      public void run() {
        for (int i = 0; i < 1000; i++) {
          System.out.println("===> " + i);
        }
      }
    }.start();

    System.out.println("스레드 실행 후");
    // "스레드 실행 후"를 출력하고 run()이 호출된다.
    // => main() 메서드의 호출이 끝나더라도 다른 스레드의 실행이 종료될 때까지
    // JVM은 종료하지 않는다.
  }
}