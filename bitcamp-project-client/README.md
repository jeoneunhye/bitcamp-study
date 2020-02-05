# 32_4 - 서버에 데이터를 요청하는 기능을 추가하기

## 학습목표

- 서버에 요청하고 응답 결과를 받을 수 있다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/domain/Board.java 추가
- src/main/java/com/eomcs/lms/handler/BoardAddCommand.java 추가
- src/main/java/com/eomcs/lms/handler/BoardListCommand.java 추가
- src/main/java/com/eomcs/lms/handler/BoardDetailCommand.java 추가
- src/main/java/com/eomcs/lms/handler/BoardUpdateCommand.java 추가
- src/main/java/com/eomcs/lms/handler/BoardDeleteCommand.java 추가
- src/main/java/com/eomcs/lms/domain/Board.java 추가
- src/main/java/com/eomcs/lms/handler/BoardAddCommand.java 추가
- src/main/java/com/eomcs/lms/handler/BoardListCommand.java 추가
- src/main/java/com/eomcs/lms/handler/BoardDetailCommand.java 추가
- src/main/java/com/eomcs/lms/handler/BoardUpdateCommand.java 추가
- src/main/java/com/eomcs/lms/handler/BoardDeleteCommand.java 추가
- src/main/java/com/eomcs/lms/domain/Board.java 추가
- src/main/java/com/eomcs/lms/handler/BoardAddCommand.java 추가
- src/main/java/com/eomcs/lms/handler/BoardListCommand.java 추가
- src/main/java/com/eomcs/lms/handler/BoardDetailCommand.java 추가
- src/main/java/com/eomcs/lms/handler/BoardUpdateCommand.java 추가
- src/main/java/com/eomcs/lms/handler/BoardDeleteCommand.java 추가
- src/main/java/com/eomcs/lms/ClientApp.java 변경

## 실습  

### 훈련 1: 프로젝트 v31에서 Domain 패키지의 Board 클래스를 가져오라.

- com.eomcs.lms.Domain 패키지 생성
- Board.java 이동

### 훈련 2: 프로젝트 v31에서 게시물 관리를 처리하는 Command 객체를 가져오라.

- BoardAddCommand.java 이동
- BoardListCommand.java 이동
- BoardDetailCommand.java 이동
- BoardUpdateCommand.java 이동
- BoardDeleteCommand.java 이동

### 훈련 3: command 객체가 서버와 통신할 수 있도록 입출력 도구를 제공하라.

- ClientApp.java 변경
  - 서버와 연결하는 코드를 적용한다.
  - 서버와 통신할 수 있는 입출력 도구를 BoardXxxCommand 객체에 제공한다.

### 훈련 4: BoardListCommand가 작업할 때 서버와 통신하도록 처리하라.

- BoardListCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경한다.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경한다.

### 훈련 5: BoardAddCommand가 작업할 때 서버와 통신하도록 처리하라.

- BoardAddCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경한다.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경한다.

### 훈련 6: BoardDetailCommand가 작업할 때 서버와 통신하도록 처리하라.

- BoardDetailCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경한다.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경한다.
  
### 훈련 7: BoardUpdateCommand가 작업할 때 서버와 통신하도록 처리하라.

- BoardUpdateCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경한다.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경한다.

### 훈련 8: BoardDeleteCommand가 작업할 때 서버와 통신하도록 처리하라.

- BoardDeleteCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경한다.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경한다.

### 훈련 9: 프로젝트 v31에서 수업 관리를 처리하는 Command 객체를 가져오라.

- LessonAddCommand.java 이동
- LessonListCommand.java 이동
- LessonDetailCommand.java 이동
- LessonUpdateCommand.java 이동
- LessonDeleteCommand.java 이동

### 훈련 10: LessonXxxCommand가 작업할 때 서버와 통신하도록 처리하라.

- LessonXxxCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경한다.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경한다.

### 훈련 11: 프로젝트 v31에서 회원 관리를 처리하는 Command 객체를 가져오라.

- MemberAddCommand.java 이동
- MemberListCommand.java 이동
- MemberDetailCommand.java 이동
- MemberUpdateCommand.java 이동
- MemberDeleteCommand.java 이동

### 훈련 12: MemberXxxCommand가 작업할 때 서버와 통신하도록 처리하라.

- MemberXxxCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경한다.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경한다.