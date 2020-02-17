// 스레드 재사용 전
package com.eomcs.concurrent.ex6;

import java.util.Scanner;

public class Exam0110 {
  public static void main(String[] args) {
    class MyThread extends Thread {
      int count;

      public void setCount(int count) {
        this.count = count;
      }

      @Override
      public void run() {
        try {
          while (true)
            for (int i = count; i > 0; i--) {
              System.out.println("==> " + i);
              Thread.sleep(1000); // 1초에 한 번씩 카운트하도록
            }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

    MyThread t = new MyThread();

    Scanner keyScan = new Scanner(System.in);

    while (true) { // "quit"을 입력하기 전까지는 main 스레드가 계속 실행됨
      System.out.println("카운트? ");
      String str = keyScan.nextLine();
      if (str.equals("quit")) {
        break; // 반복문을 끝내고 "main 스레드 종료"가 출력됨
      }

      int count = Integer.parseInt(str); // str이 숫자라고 가정하자.
      t.setCount(count);
      t.start(); // t 스레드 생성
    } // 스레드를 실행한 즉시 다음 카운트를 입력받는다.
    // Dead 상태의 스레드를 다시 시작하려 하면 예외가 발생한다.
    // run() 메서드를 한번 호출하여 Dead 상태가 된 스레드는
    // 다시 시작시킬 수 없다!
    // 주의!
    // 이미 실행중인 스레드 객체에 대해 start()를 또 호출하면 예외가 발생한다.

    System.out.println("main 스레드 종료!");
  }
}