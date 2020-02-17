// 스레드 재사용 II - sleep()/timeout을 활용한 스레드 재사용
package com.eomcs.concurrent.ex6;

import java.util.Scanner;

public class Exam0130 {
  public static void main(String[] args) {
    class MyThread extends Thread {
      boolean enable;
      int count;

      public void setCount(int count) {
        this.count = count;

        // 카운트를 설정하면 스레드의 작업을 허락한다는 뜻에서 true로 만들자.
        this.enable = true;
      }

      @Override
      public void run() {
        System.out.println("스레드 시작했음!");

        try {
          while (true) {
            System.out.println("스레드를 10초동안 잠들게 한다!");
            Thread.sleep(10000);

            // 무조건 작업하지 말고 enable이 true일 때만 작업하게 하자!
            if (!enable) {
              continue; // 다시 Thread.sleep(10000);
            }

            System.out.println("카운트 시작!");
            for (int i = count; i > 0; i--) {
              System.out.println("==> " + i);
              Thread.sleep(1000);
            }

            enable = false; // 스레드에게 맡긴 작업이 끝나면 enable은 false로 설정
            // 비활성 상태로 설정한 후 10초동안 잠든다.
          }

        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

    MyThread t = new MyThread();

    // 미리 스레드를 시작시켜 놓는다.
    t.start();

    Scanner keyScan = new Scanner(System.in);

    while (true) {
      System.out.println("카운트? ");
      String str = keyScan.nextLine();
      if (str.equals("quit")) {
        break;
      }

      int count = Integer.parseInt(str);
      t.setCount(count);
      // 이전 버전보다는 나아졌다. 스레드가 작업을 완료하면 10초동안 잠든다.
      // 10초 후에 깨어났을 때 카운트 값을 다시 설정하지 않으면
      // enable이 false인 상태기 때문에 계속해서 10초동안 잠들게 한다.
      // count를 다시 설정하면 enable이 활성화되어 잠에서 깨어나 카운트를 시작한다.
      // 이전 버전에서는 깨어난 후 무조건 작업을 수행했지만,
      // 이 버전은 카운트 값이 설정될 때만 작업하도록 개선하였다.
      // 그러나 근본적인 문제는 해결되지 않았다.
      // 작업을 완료한 후 무조건 10초를 기다린다.
      // 작업이 있어야 작업 수행 전 10초를 기다리는데, 스레드가 깨어난 후 작업이 없더라도 10초를 기다린다.
    }

    System.out.println("main 스레드 종료!");
  }
}