package com.eomcs.algorithm.data_structure.array;

import com.eomcs.lms.domain.Member;

public class ArrayListTest {
  public static void main(String[] args) {
    //ArrayList a1 = new ArrayList(); // ArrayList의 기본 생성자를 호출
    ArrayList<String> a1 = new ArrayList();
    //ArrayList a2 = new ArrayList(100); // ArrayList의 초기 크기를 100으로 설정하여 호출

    // ArrayList 클래스의 메서드를 호출할 때 어떤 인스턴스를 사용할 건지(몇 번지로 갈 건지) 앞쪽에 알려준다.
    a1.add("aaa");
    a1.add("bbb");
    a1.add("ccc");
    a1.add("ddd");
    a1.add("eee");
    a1.add("fff");
    //a1.add(new Member());   // 컴파일 오류-String 데이터만 사용 가능

    System.out.println(a1.get(-1));
    System.out.println(a1.get(6));


    String oldValue = /*(String)*/ a1.get(0);
    a1.set(0, "xxx");
    System.out.printf("%s ==> %s\n", oldValue, a1.get(0));
    a1.set(-1, "yyy");
    a1.set(6, "zzz");

    //15. set() 메서드 변경 후
    oldValue = /*(String)*/ a1.set(0, "XXX");
    System.out.printf("%s ==> %s\n", oldValue, a1.get(0));


    System.out.println("-----------------");

    String value = /*(String)*/ a1.get(0);
    // a1의 0번째 element 배열 값을 꺼내줘, 그리고 리턴받은 Object를 String으로 형변환해줘
    // Integer value2 = (Integer)a1.get(0); 데이터 타입을 맞춰줘야 한다! 실행단계까지 속일 수 없다. runtime error
    System.out.println(value);

    System.out.println(a1.get(0)); // a1.get(0); 메서드를 호출한 다음 리턴받은 값을 출력

    System.out.println(a1.get(2));

    System.out.println("-----------------");
    print(a1);
    a1.remove(-1);
    a1.remove(0);
    System.out.printf("삭제 전(%s) ==> 삭제 후(%s)\n", oldValue, a1.get(0));
    System.out.println("-----------------");
    // 17. remove() 메서드 변경 후
    oldValue = /*(String)*/ a1.remove(0);
    System.out.printf("삭제 전(%s) ==> 삭제 후(%s)\n", oldValue, a1.get(0));
    print(a1);
  }

  static void print(ArrayList<String> arr) {
    // 23. 제네릭으로 지정된 타입의 배열을 리턴하는 toArray(E[] arr)를 추가한 후
    String[] list = arr.toArray(new String[] {});
    for (String e : list) {
      System.out.println(e);
    
    /* 20. toArray() 메서드 정의 후
    Object[] list = arr.toArray();
    for (Object e : list) { // 배열의 전체를 출력
      System.out.println(e);
      */
      //for (int i = 0; i < arr.size(); i++) {
      //System.out.println(arr.get(i));
    }
  }
}