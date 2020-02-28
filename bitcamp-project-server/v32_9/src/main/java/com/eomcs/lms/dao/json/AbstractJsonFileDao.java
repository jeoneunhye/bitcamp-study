package com.eomcs.lms.dao.json;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public abstract class AbstractJsonFileDao<T> {
  // 서브 클래스가 접근할 수 있도록 protected 선언
  protected List<T> list;
  protected String filename;

  public AbstractJsonFileDao(String filename) {
    this.filename = filename;
    list = new ArrayList<>();
  }

  @SuppressWarnings("unchecked")
  public void loadData() {
    File file = new File(filename);

    try (BufferedReader in = new BufferedReader(new FileReader(file))) {

      Class<?> currType = this.getClass();
      // 현재 클래스의 정보를 알아낸다.
      System.out.println(currType);

      Type parentType = currType.getGenericSuperclass();
      // 제네릭 타입의 수퍼 클래스 정보를 알아낸다. java.lang.reflect
      System.out.println(parentType);

      ParameterizedType parentType2 = (ParameterizedType) parentType;
      // 수퍼 클래스의 타입 파라미터 중에서 T 값을 추출한다.
      // => 수퍼 클래스에 제네릭이 적용된 경우 실제 다음과 같다.

      Type[] typeParams = parentType2.getActualTypeArguments();
      // => 제네릭 수퍼 클래스 정보로부터 "타입 파라미터" 목록을 꺼낸다.
      // => 예를 들어 수퍼 클래스가 다음과 같다면,
      //      class My<T,S,U,V> {...}
      //    타입 파라미터 목록은 T, S, U, V다.
      // => 그런데 AbstractJsonFileDao 클래스는 타입 파라미터가 한 개다.
      //    따라서 리턴되는 배열에는 T 타입 한 개의 정보가 있다.

      // 여기서 우리가 관심있는 T 타입을 배열의 0번 방에서 꺼낸다.
      Type itemType = typeParams[0];
      System.out.println(itemType);

      // T가 실제 어떤 타입인지 알아냈으면 이것을 가지고 배열을 만들자.
      // => 크기가 0인 배열을 생성한다.
      // => 실제 배열을 사용하려는 것이 아니라 배열의 타입을 꺼내기 위함이다.
      T[] arr = (T[]) Array.newInstance((Class)itemType, 0);

      // T 타입의 배열 정보를 가지고 JSON 데이터를 읽는다.
      // 리턴 값은 실제 T 타입의 객체가 들어 있는 배열이다.
      T[] dataArr = (T[]) new Gson().fromJson(in, arr.getClass());
      for (T b : dataArr) {
        list.add(b);
      }

      System.out.printf("총 %d개의 객체를 로딩했습니다.\n", list.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void saveData() {
    File file = new File(filename);

    try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
      out.write(new Gson().toJson(list));

      System.out.printf("총 %d개의 객체를 저장했습니다.\n", list.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());

    }
  }

  protected abstract <K> int indexOf(K key);
}