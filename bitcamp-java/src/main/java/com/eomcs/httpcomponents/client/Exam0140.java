// Apache HttpComponents 사용법 : HttpClient5 - GET 요청하기 IIII
package com.eomcs.httpcomponents.client;

import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;

public class Exam0140 {
  public static void main(String[] args) throws Exception {
    // 더 간결하게 HTTP 요청하기
    // => httpclient-fluent 라이브러리를 추가해야 한다.
    // - mvnrepository.com 또는 search.maven.org에서 'httpclient5-fluent' 키워드로 검색한다.
    // - 최신 라이브러리 정보를 build.gradle의 dependencies {} 블록에 추가한다.
    //   implementation 'org.apache.httpcomponents.client5:httpclient5-fluent:5.0'
    //   의존 라이브러리는 자동 다운로드하기 때문에 기존의 apache 라이브러리 정보는 주석처리한다.
    Content result = Request.get("https://www.daum.net").execute().returnContent();
    System.out.println(result.toString());
  }
}