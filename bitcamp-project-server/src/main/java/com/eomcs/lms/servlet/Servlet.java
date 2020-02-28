package com.eomcs.lms.servlet;
// server + let 서블릿 인터페이스
// 클라이언트의 요청을 받아 데이터를 처리하는 각각의 클래스를 생성하고
// 그 클래스들의 구현 객체는 입출력 스트림을 파라미터로 받는 service()라는 메서드를 호출한다.
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface Servlet {
  void service(ObjectInputStream in, ObjectOutputStream out) throws Exception;
}