// Building을 상속받아 템플릿 메서드인 startEffect()와 endEffect()를 구체화시킨다.
package com.eomcs.oop.ex10.c;

public class Farm extends Building { // Building의 서브 클래스에서 세세한 작업을 정의
  public void startEffect() {
    System.out.println("땅을 고른다...");
  }

  public void endEffect() {
    System.out.println("씨앗을 뿌린다...");
  }
}