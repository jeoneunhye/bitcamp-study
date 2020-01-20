// java.util.concurrent.ArrayBlockingQueue 사용
package com.eomcs.corelib.ex06;
// Queue는 인터페이스라서 직접 사용할 수 없다.
import java.util.concurrent.ArrayBlockingQueue;

public class Exam0120 {
  public static void main(String[] args) {
    String s1 = new String("aaa");
    String s2 = new String("bbb");
    String s3 = new String("ccc");
    String s4 = new String("ddd");
    String s5 = new String("eee");

    ArrayBlockingQueue queue = new ArrayBlockingQueue(100);
    queue.offer(s1); // aaa,
    print(queue);
    queue.offer(s2); // aaa, bbb,
    print(queue);
    queue.offer(s3); // aaa, bbb, ccc,
    print(queue);

    System.out.println("==>" + queue.poll()); // ==>aaa
    print(queue); // bbb, ccc,
    System.out.println("==>" + queue.poll()); // ==>bbb
    print(queue); // ccc,

    queue.offer(s4); // ccc, ddd,
    print(queue);
    queue.offer(s5); // ccc, ddd, eee,
    print(queue);

    String value;
    while ((value = (String) queue.poll()) != null) {
      System.out.println(value); // ccc 줄바꿈 ddd 줄바꿈 eee
    }
  }

  static void print(ArrayBlockingQueue list) {
    Object[] arr = list.toArray();
    for (Object value : arr) {
      System.out.print(value + ", ");
    }
    System.out.println();
  }
}