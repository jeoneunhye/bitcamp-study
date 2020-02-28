package com.eomcs.context;

import java.util.Map;
// 애플리케이션의 상태가 변경되었을 때
// 호출할 메서드 규칙을 정의한다.
// 즉 애플리케이션 상태 변경에 대해 보고받을 "Observer" 규칙을 정의한다.
// 보통 옵저버를 "리스너(listener)"라 부른다.
public interface ApplicationContextListener {
  // 애플리케이션(호출자)에서 옵저버를 실행하고 그 결과를 받을 수 있도록
  // 옵저버의 메서드를 호출할 때 파라미터로 Map 객체를 전달하도록 정의한다.
  // 호출자가 옵저버의 실행 결과를 받을 수 있도록 파라미터로 맵 객체를 전달할 것이다.
  // 리턴 값으로 결과를 전달하지 않고 파라미터로 넘어온 저장소에 보관하는 방법을 많이 사용한다.
  // Map을 사용하면 외부에서 값을 받는 용도로만 사용하는 것이 아니라
  // 리턴 값을 넘겨줄 수도 있다. (데이터의 in/out이 가능)

  // 애플리케이션이 시작될 때 호출된다.
  void contextInitialized(Map<String, Object> context);

  // 애플리케이션이 종료될 때 호출된다.
  void contextDestroyed(Map<String, Object> context);
}