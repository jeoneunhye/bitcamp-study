package com.eomcs.oop.test;
// gym 회원 정보를 입력받아 출력
public class Test07 {
  public static void main(String[] args) {
    // 회원 정보를 담을 수 있는 메모리의 설계도 : 클래스
    class Member {
      int no;
      String name;
      int birthYear;
      char gender;
      float height;
      float weight;
      boolean personalTraining;
    }
    Member m1;
    m1 = new Member();          // 밑에서 새 인스턴스 주소를 사용하기 때문에 garbage가 됨
    m1.no = 100;
    
    Member m2 = m1;

    m1 = new Member();          //새 인스턴스 주소를 사용하였기 때문에 m1.no의 값이 0으로 초기화됨
    m1.no = 200;

    m2 = m1;
    // -> 이제 m2도 m1과 같은 인스턴스 주소를 갖고 있다.
    // 즉 같은 인스턴스를 가리킨다.
    // 처음에 만들었던 인스턴스는 주소를 잃어버려 사용할 수 없다.
    // 이렇게 주소를 잃어버려 사용할 수 없는 인스턴스를 "가비지(garbage)"라 부른다.
  }
}
