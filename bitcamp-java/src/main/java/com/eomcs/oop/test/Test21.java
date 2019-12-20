package com.eomcs.oop.test;
// gym 회원 정보를 입력받아 출력
public class Test21 {
  // 회원 정보를 담을 수 있는 메모리의 설계도 : 클래스
  static class Member { // 다른 메서드와 공유할 클래스기 때문에 main 메서드 위로 뽑는다
    int no;
    String name;
    int birthYear;
    char gender;
    float height;
    float weight;
    boolean personalTraining;
  }
  public static void main(String[] args) {
    // 인스턴스 주소 주고 받기
    
    // 1) 인스턴스 주소 받기
    Member m1;
    m1 = createMember();        // 2) createMember에서 리턴받은 m값을 m1에 저장한다
    
    // 3) 인스턴스의 주소 넘기기
    setMemberValues(m1);    // 3) m1에 저장된 멤버 인스턴스 주소를 넘기겠다
    printMember(m1);    // 5) 저장된 값을 출력해
    // call method 메소드 호출하는 것
  }
  static Member createMember() {  // static 다음 void 자리에 리턴할 주소?변수?를 입력해준다!
    Member m = new Member();
    return m;               // 1) m값을 리턴
  }
  static void setMemberValues(Member m) {
    // 4) 인스턴스를 외부에서 받아 받은 주소에 찾아가서 해당 인스턴스 no,name,birthYear의 값을 각각 저장한다
    m.no = 100;
    m.name = "홍길동";
    m.birthYear = 2000;
  }
  static void printMember(Member m) {
    // Member m : 외부에서 호출될 때 넘겨받은 변수. 로컬 변수 중에서도 파라미터
    System.out.println(m.no);
    System.out.println(m.name);
    System.out.println(m.birthYear);
  }
}
