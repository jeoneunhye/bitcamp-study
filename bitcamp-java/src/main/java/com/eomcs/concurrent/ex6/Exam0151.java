// 멀티 스레드 재사용 - Pooling 기법(=flyweight design pattern)을 이용하여 생성된 객체를 재활용하기
package com.eomcs.concurrent.ex6;

import java.util.Scanner;
import com.eomcs.algorithm.data_structure.array.ArrayList;

public class Exam0151 {
  // 메인 밖으로 빼내고 스태틱 클래스로 변경
  static class MyThread extends Thread {
    ThreadPool pool;
    int count;

    public MyThread(String name, ThreadPool pool) {
      super(name);
      this.pool = pool; // 어느 스레드풀 소속인지 받음, 일을 다했을 때 해당 주소의 풀로 돌아가기 위해
      // 마이스레드풀은 마이 스레드를 알지만 마이스레드는 마이스레드 풀을 몰라 다만 인터페이스는 알아?
    }

    public void setCount(int count) {
      this.count = count;

      synchronized (this) {
        notify();
      }
    }

    @Override
    public void run() {
      synchronized (this) {
        System.out.println("스레드 시작했음!");

        try {
          while (true) {
            System.out.println("스레드 대기중...");
            // 작업하라는 알림이 올 때까지 기다린다.
            wait();

            // 알림이 오면 작업을 실행한다.
            for (int i = count; i > 0; i--) {
              System.out.printf("[%s] %d\n", getName(), i);
              Thread.sleep(2000);
            }

            // 스레드의 작업이 끝났으면 스레드 풀로 돌아간다.
            pool.add(this);
          }

        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  interface ThreadPool<E> {
    Thread get();
    void add(Thread obj);
  }

  // 메인 밖으로 빼내고 스태틱 클래스로 변경
  // ThreadPool 인터페이스를 구현
  static class MyThreadPool implements ThreadPool {
    ArrayList<MyThread> list = new ArrayList<>();

    public MyThreadPool() {
      // 스레드를 생성하고 시작시킨 상태에서 리스트에 담는다.
      MyThread t1 = new MyThread("1번 스레드=>", this); // 너는 내 소속이야
      t1.start();
      list.add(t1);

      MyThread t2 = new MyThread("2번 스레드**>", this);
      t2.start();
      list.add(t2);

      MyThread t3 = new MyThread("3번 스레드--->", this);
      t3.start();
      list.add(t3);
    } // 시작했지만 run에서 기다리게 했어

    @Override
    public MyThread get() { // 리턴 값이 스레드가 아닌 스레드의 서브 클래스를 지정해도 된다.
      // 그러나 add와 같이 파라미터로 받을 때는 꼭 Thread로 해야 한다.
      if (list.size() > 0) {
        return list.remove(0); // get이 아닌 아예 꺼내는 것(스레드를 사용하면 사라지니까?)
        // 1번 스레드가 작업하고 끝나면 add하는데, 그때 리스트의 맨뒤에 들어가기 때문에
        // 다음 get을 호출하면 2번 스레드가 작업을 한다.
      }

      return null;
    }

    // 스레드를 다 쓴 후에는 다시 스레드 풀에 돌려준다.
    @Override
    public void add(/*My*/Thread t) { // 마이스레드풀의 특정 메서드에서 마이스레드를 파라미터로 받아 사용(포함관계)
      // 컴퓨터와 클리너 인스턴스 필드에 보관할 필요가 없다.
      // => Dependency
      // * 인스턴스 필드로 사용하는 관계: 의존관계(association)
      // 포함하면서 인스턴스 필드로 사용하면 aggregation(집합) 포함된 컨테이너가 종료되어도 끝나지 않아 (컴퓨터와 마욱스)
      // 포함하면서 구현체를 사용하면 composition(합성) 컨테이너가 종료되면 같이 종료 (컴퓨터와 CPU) 일체화된 상태
      list.add((MyThread) t); // 받은 마이스레드를 사용한다기보다 리스트를 사용하는 것이다. => 포함관계 aggregation
      // mythread.run()에서 작업을 수행하고 run 안에서 threadpool.add(this)를 호출해야 풀에 들어간다.
      // 크로스 참조가 발생하지 않도록 중간에 인터페이스를 둔다. (유지보수- 종속성을 줄이기 위해)
      // 마이스레드가 마이스레드풀을 사용하는것이 아닌 스레드풀 인터페이스를 사용하도록 만든다.
      // 마이스레드풀은 스레드풀 인터페이스를 구현
      // 인터페이스여도 되고 수퍼 클래스여도 된다.
    }
  }

  public static void main(String[] args) {
    //MyThread t = new MyThread("유관순");

    // 미리 스레드를 시작시켜 놓는다.
    //t.start();

    // 스레드 풀 준비
    MyThreadPool threadPool = new MyThreadPool();

    Scanner keyScan = new Scanner(System.in);

    while (true) {
      System.out.println("카운트? ");
      String str = keyScan.nextLine();
      if (str.equals("quit")) {
        break;
      }

      int count = Integer.parseInt(str);

      // 스레드 풀에서 스레드를 한 개 꺼낸다.
      MyThread t = threadPool.get();
      if (t == null) {
        System.out.println("남는 스레드가 없습니다!");
        continue;
      }

      // 스레드의 카운트를 설정한다. 그러면 카운트를 시작할 것이다.
      t.setCount(count);
    }

    System.out.println("main 스레드 종료!");
  }
}