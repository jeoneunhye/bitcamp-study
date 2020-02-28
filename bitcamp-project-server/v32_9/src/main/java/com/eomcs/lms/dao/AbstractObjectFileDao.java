package com.eomcs.lms.dao;
// 최소 공통 메서드인 loadData(), saveData(), indexOf()만을 구현
// 나머지 메서드는 각각의 구현 방법이 다를 수 있기 때문에 서브 클래스에서 구현한다.
// 서브 클래스에게 공통 필드나 메서드를 상속해주는 역할 => abstract
// 서브 클래스에게 상속해주는 메서드 중에서
// indexOf()처럼 구현되지 않은 메서드가 존재 => abstract
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractObjectFileDao<T> {
  // 서브 클래스가 접근할 수 있도록 protected 선언
  protected List<T> list;
  protected String filename;

  public AbstractObjectFileDao(String filename) {
    this.filename = filename;
    list = new ArrayList<>();

    loadData();
  }

  @SuppressWarnings("unchecked")
  public void loadData() {
    File file = new File(filename);

    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(file)))) {
      list = (List<T>) in.readObject();

      System.out.printf("총 %d개의 객체 데이터를 로딩했습니다.\n", list.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  public void saveData() {
    File file = new File(filename);

    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(file)))) {
      out.reset();
      out.writeObject(list);

      System.out.printf("총 %d개의 객체 데이터를 저장했습니다.\n",
          list.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생 - " + e.getMessage());
    }
  }

  // 서브 클래스에서 접근할 수 있도록 modifier를 protected로 선언한다.
  // 만약 int no를 받지 않는 Domain이 있다면?
  // 위에 클래스명에 제네릭 타입을 <T,K>라고 정의해도 되고
  // 아래처럼 리턴 타입 옆에 <K>라고 정의해도 된다.
  // 파라미터의 K는 클래스명이 아니라 제네릭이라고 알려주는 것이다.
  // 서브 클래스마다 구현 방법이 달라서 수퍼 클래스에서 메서드를 구현할 수 없다면
  // abstract method로 선언한다.
  protected/*private*/ abstract <K> int indexOf(K key/*int no*/);
}