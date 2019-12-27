package com.eomcs.oop.ex02.study;
// + Test04.java
public class GasStation {
  // 무슨 종류의 주유소냐 휘발유? 가스? 값은 메인 메서드에서 저장한다
  /*static*/int type;
  String name;  // name 필드 추가-주유소 이름의 개별 관리가 가능
  
  /*static*/ int refuel(Car c) { // 주유
    if (c.type != this.type)    //인스턴스 메서드가 됨 -> this를 추가
      // 클래스 메서드인 상태에서 파라미터에 GasStation station을 주고
      // station.type으로 해도 된다. 더 간략하게 쓰기 위해 인스턴스 메서드로 바꿔준 것
      return 0;
    
    // 돈받으려면 얼마만큼 채웠는지 알아야 한다 -> 리턴값이 필요하다
    int amount = 100 - c.energy;    // 잔류량을 나타내줌
    c.energy = 100;
    return amount;
  }
  
  // 인스턴스 변수를 쓰지 않는 메서드이기 때문에(주유소 type이 뭐든 관계가 없기 때문에)
  // 굳이 static을 빼지 않아도 된다.
  static void clean(Car c) { // 세차
    // 깨끗하게 씻기만 하면 되기 때문에 리턴값을 주지 않아도 된다
    c.cleanLevel = 0;
  }
}