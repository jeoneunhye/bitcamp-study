// local class - 로컬 클래스가 로컬 변수를 조회용으로만 사용하는 이유?
package com.eomcs.oop.ex11.a;

public class Exam0360 {
  public static void main(final String[] args) {
    final Exam0360 obj = new Exam0360();
    final Runner r = obj.createRunner("홍길동");
    // createRunner()의 리턴 값은
    // 이 메서드에 선언된 로컬 클래스인 객체 A이다.
    // A 객체는 Runner 규칙에 따라 만들었기 때문에
    // Runner 레퍼런스에 저장할 수 있다.
    // 이 레퍼런스를 사용하여 A 객체의 run() 메서드를 호출해 보자!
    r.run();

    // 로컬 클래스의 객체가 로컬 변수의 값을 조회용으로만 사용하는 이유?
    // 위의 코드에서 createRunner() 호출이 끝나면 이 메서드가 실행되는 동안
    // 생성되었던 모든 로컬 변수는 스택 메모리에서 제거된다.
    // 즉 createRunner()의 name 파라미터가 제거된다는 것이다.
  }

  Runner createRunner(final String name) {
    // Exam0360 객체 주소를 받는 내장 변수 this가 있다.
    class A implements Runner { // 로컬 클래스 A
      // 컴파일러는 바깥 클래스의 객체 주소를 받을 필드를 추가한다.
      // 또한 바깥 클래스의 객체 주소를 받는 생성자를 추가한다.
      /*
       Exam0360 this; 바깥 클래스의 주소를 받을 필드
       String paramName; // run() 에 필요한 name을 복제
       
       public A(Exam0360 obj, String str) {
       this = obj;
       paramName = str;
       }
       */
      @Override
      public void run() {
        // name = "aaa"; // 컴파일 오류! 값을 변경할 수 없다.
        // 바깥 메서드의 로컬 변수는 메서드 호출이 완료되는 순간
        // 스택 메모리에서 제거되기 때문에
        // 로컬 클래스의 객체에서 사용할 수 없는 상황이 발생할 것이다.
        // 그래서 컴파일러는 바깥 메서드의 로컬 변수를
        // 로컬 객체를 생성하는 시점에서 해당 변수의 값으로 대체한다.
        // 호출할 당시에 들어있는 값으로 대체된다.
        System.out.printf("%s님이 달립니다!", name);
        // 위 문장은 컴파일러가 로컬 클래스의 필드(ex: paramName)를 사용하는 문장으로 바꾼다.
        // 그래서 createRunner() 메서드 호출이 끝나더라도
        // name 값을 사용할 수 있는 것이다.
        // 로컬 변수 name이 아닌, 필드를 별도로 복제한 paramName이 있어 stack에서 run()의 메모리가 사라져도 사용이 가능
        // 다음 코드의 name은 createRunner() 메서드의 파라미터가 아니다.
        // A 인스턴스가 생성될 때 값을 따로 복제한 필드를 가리키는 것이다.
        // System.out.printf("%s님이 달립니다!", this.paramName);
      }
    }
    return new A();
    // 컴파일러는 로컬 클래스의 객체를 생성할 때
    // 바깥 클래스의 객체 주소와
    // 로컬 객체가 사용하는 메서드의 로컬 변수 값을 전달하기 위해
    // 다음과 같이 컴파일러가 만든 생성자를 호출한다.
    // new A(this, name);
  }
}

interface Runner {
  void run();
}