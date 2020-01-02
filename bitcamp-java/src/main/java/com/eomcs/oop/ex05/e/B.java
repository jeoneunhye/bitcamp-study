package com.eomcs.oop.ex05.e;

// 상속
// => 재사용할 기존 클래스를 지정한다.
// => 새 클래스에는 추가할 기능을 덧붙인다.
// 주의! 개발 입문자들이 가장 많이 착각하는 것!
// => 상속이라는 단어 때문에 개발 입문자들이 많이 오해한다.
//    "B 클래스가 A 클래스를 상속했기 때문에,
//     B 클래스는 A 클래스의 코드를 갖고 있을 것이다." 'B 클래스에 다 들어있겠구나!'
// => 아니다!
//    B 클래스는 단지 A 클래스의 링크 정보만 갖고 있다. invokespecial com.eomcs.oop.ex05.e.A()
//    따라서 B 클래스를 사용하려면 반드시 A 클래스가 있어야 한다. 그대로 복사된 것이 아니다.
//    bin 폴더에서 A.class를 삭제하면 Exam01.java를 실행할 수 없다! classNotFound
public class B extends A {
    int v2;
    
    static {
        System.out.println("B클래스의 static{} 실행!");
    }
}