# 32_4 - 서버에 데이터를 요청하는 기능을 추가하기

## 학습목표

- 서버에 요청하고 응답 결과를 받을 수 있다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/domain/Lesson.java 추가
- src/main/java/com/eomcs/lms/domain/Member.java 추가
- src/main/java/com/eomcs/lms/domain/Board.java 추가
- src/main/java/com/eomcs/lms/handler/LessonXxxCommand.java 추가
- src/main/java/com/eomcs/lms/handler/MemberXxxCommand.java 추가
- src/main/java/com/eomcs/lms/handler/BoardXxxCommand.java 추가
- src/main/java/com/eomcs/lms/ClientApp.java 변경

## 실습  

### 훈련 1: 31번 프로젝트에서 Domain 클래스를 가져오라.

- com.eomcs.lms.domain 패키지 생성
- Lesson.java 추가
- Member.java 추가
- Board.java 추가

### 훈련 2: 31번 프로젝트에서 수업, 회원, 게시물 관리를 처리하는 Command 객체 가져오라.

- com.eomcs.lms.handler 패키지 생성
- LessonXxxCommand.java 추가
- MemberXxxCommand.java 추가
- BoardXxxCommand.java 추가

### 훈련 3: Command 객체가 서버와 통신할 수 있도록 입출력 도구를 제공하라.

- ClientApp.java 변경
  - 서버와 연결하는 코드를 적용한다.
  - 서버와 통신할 수 있는 입출력 도구를 각각의 XxxCommand 객체에 제공한다.
  
### 훈련 4: XxxListCommand가 작업할 때 서버와 통신하도록 처리하라.

- XxxListCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경한다.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경한다.

### 훈련 5: XxxAddCommand가 작업할 때 서버와 통신하도록 처리하라.

- XxxAddCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경한다.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경한다.

### 훈련 6: XxxDetailCommand가 작업할 때 서버와 통신하도록 처리하라.

- XxxDetailCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경한다.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경한다.
  
### 훈련 7: XxxUpdateCommand가 작업할 때 서버와 통신하도록 처리하라.

- XxxUpdateCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경한다.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경한다.
  
### 훈련 8: XxxDeleteCommand가 작업할 때 서버와 통신하도록 처리하라.

- XxxDeleteCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경한다.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경한다.