// 논리 연산자 : || vs |
package com.eomcs.basic.ex05;

public class Exam03_4 {
    public static void main(String[] args) {
        boolean a = true;
        boolean b = false;
        boolean r = a || (b = true);
        // || 왼쪽 값으로 이미 결과를 알 수 있기 때문에
        // || 오른쪽 문장은 실행하지 않는다.
        System.out.printf("a=%b, b=%b, r=%b\n", a, b, r);

        a = true;
        b = false;
        r = a | (b = true);
        // | 왼쪽 값으로 결과를 확정할 수 있더라도 무조건 오른쪽 문장을 실행한다.
        System.out.printf("a=%b, b=%b, r=%b\n", a, b, r);
    }
}
