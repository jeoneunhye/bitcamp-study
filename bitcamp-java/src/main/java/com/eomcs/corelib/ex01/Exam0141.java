// Object 클래스 - hashCode() 오버라이딩
package com.eomcs.corelib.ex01;

public class Exam0141 {
  static class My {
    String name;
    int age;
    
    @Override
    public int hashCode() {
      String str = String.format("%s,%d", this.name, this.age);
      return str.hashCode();
    }
  }
  
  public static void main(String[] args) {
    My obj1 = new My();
    obj1.name = "홍길동";
    obj1.age = 20;
    
    My obj2 = new My();
    obj2.name = "홍길동";
    obj2.age = 20;
    
    System.out.println(obj1 == obj2); // false
    System.out.println(obj1.equals(obj2)); // false
    
    System.out.println(Integer.toHexString(obj1.hashCode())); // 994aa0fc
    System.out.println(Integer.toHexString(obj2.hashCode())); // 994aa0fc
    
    System.out.println(obj1); // com.eomcs.corelib.ex01.Exam0141$My@994aa0fc
    System.out.println(obj2); // com.eomcs.corelib.ex01.Exam0141$My@994aa0fc
  }
}