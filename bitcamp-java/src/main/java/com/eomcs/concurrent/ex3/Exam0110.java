// 스레드 만들기 I - Thread를 상속받기
package com.eomcs.concurrent.ex3;

public class Exam0110 {
  public static void main(String[] args) {
    // 1) Thread 클래스를 상속받아 정의하기
    // => 구현하기 편하다.
    // => 그런데 다중 상속이 불가능하기 때문에 다른 클래스를 상속받을 수 없다.
    // => 즉 MyThread가 다른 클래스를 상속받으면서 스레드가 될 순 없다.
    class MyThread extends Thread {
      @Override
      public void run() {
        for (int i = 0; i < 1000; i++) {
          System.out.println("===> " + i);
        }
      }
    }

    // 스레드 실행
    // => Thread의 서브 클래스는 그냥 인스턴스를 만들어 start()를 호출한다.
    MyThread t = new MyThread();
    t.start(); // 실행 흐름을 분리한 후 즉시 리턴한다. 비동기로 동작한다.

    // "main" 스레드는 MyThread와 상관없이 병행하여 실행한다.
    for (int i = 0; i < 1000; i++) {
      System.out.println(">>>> " + i);
    }
  }
}

// 스레드에게 CPU 사용을 배분할 때 순서대로 하지 않는다.
// OS의 CPU 스케줄링 정책에 따라 스레드가 실행된다.
// 즉 JVM에서 스레드를 실행하는 것이 아니라 OS가 실행한다.
// 결론!
// => 똑같은 자바의 스레드 코드가 OS에 따라 실행 순서가 달라질 수 있다.

// 우선 순위로 조정하면 되지 않나요?
// => Windows OS의 경우 우선 순위(priority) 값이 실행 순서나 실행 횟수에 큰 영향을 끼치지 않는다.
// 그래서 우선 순위의 값을 조정하여 스레드의 실행 횟수를 조정하려 해서는 안 된다.
// => 왜? OS에 따라 실행 정책이 다르기 때문이다.
// => 그냥 특정 코드를 동시에 실행하고 싶을 때 스레드를 사용한다고 생각하라!