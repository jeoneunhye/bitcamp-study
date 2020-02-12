# 32_3 - LMS 명령을 입력받는 기능을 추가하기

## 학습목표

- 사용자로부터 명령을 입력받을 수 있다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/util/Prompt.java 추가
- src/main/java/com/eomcs/lms/Handler/Command.java 추가
- src/main/java/com/eomcs/lms/ClientApp.java 변경

## 실습  

### 훈련1: 프로젝트 v31의 App 클래스에서 명령을 입력받는 부분을 가져오라.

- com.eomcs.util 패키지 생성
- com.eomcs.util.Prompt 클래스 이동
- com.eomcs.lms.handler 패키지 생성
- com.eomcs.lms.handler.Command 인터페이스 이동
- ClientApp.java 변경
  - 사용자가 입력한 명령을 처리하는 코드를 가져온다.