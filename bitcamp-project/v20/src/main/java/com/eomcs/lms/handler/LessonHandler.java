package com.eomcs.lms.handler;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.ArrayList;
import com.eomcs.util.LinkedList;
import com.eomcs.util.Prompt;

public class LessonHandler {
  LinkedList<Lesson> lessonList;
  
//  public Scanner input;
  Prompt prompt;

  public LessonHandler(Prompt prompt/*Scanner input*/) {
    this.prompt = prompt;
//    this.input = input;
    lessonList = new LinkedList<>();
  }

  public void addLesson() {
    Lesson lesson = new Lesson();

    lesson.setNo(prompt.inputInt("번호? "));
    lesson.setTitle(prompt.inputString("수업명? "));
    lesson.setDescription(prompt.inputString("수업내용? "));
    lesson.setStartDate(prompt.inputDate("시작일? "));
    lesson.setEndDate(prompt.inputDate("종료일? "));
    lesson.setTotalHours(prompt.inputInt("총수업시간? "));
    lesson.setDayHours(prompt.inputInt("일수업시간? "));

    lessonList.add(lesson);
    System.out.println("저장하였습니다.");
  }

  public void listLesson() {
    // 수업 객체 목록을 복사받을 배열을 준비하고 toArray()를 실행한다.
    // toArray()의 리턴 값은 파라미터로 넘겨준 배열의 주소와 같다.
    Lesson[] arr = this.lessonList.toArray(new Lesson[this.lessonList.size()]);
    for (Lesson l : arr) {
      System.out.printf("%d, %s, %s ~ %s, %d\n",
          l.getNo(), l.getTitle(), l.getStartDate(), l.getEndDate(), l.getTotalHours());
    }
  }

  public void detailLesson() {
    //    System.out.println("번호? ");
    //    int no = input.nextInt();
    //    int index = indexOfLesson(no);

    int index = indexOfLesson(prompt.inputInt("번호? "));

    if (index == -1/*lesson == null*/) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
      return;
    }

    Lesson lesson = this.lessonList.get(index);

    System.out.printf("번호: %d\n", lesson.getNo());
    System.out.printf("수업명: %s\n", lesson.getTitle());
    System.out.printf("수업내용: %s\n", lesson.getDescription());
    System.out.printf("기간: %s ~ %s\n",
        lesson.getStartDate(), lesson.getEndDate());
    System.out.printf("총수업시간: %d\n", lesson.getTotalHours());
    System.out.printf("일수업시간: %d\n", lesson.getDayHours());
  }

  public void updateLesson() {
    //    System.out.print("번호? ");
    //    int no = input.nextInt();
    //    input.nextLine();
    //    int index = indexOfLesson(no);

    int index = indexOfLesson(prompt.inputInt("번호? "));

    if (index == -1) {
      System.out.println("해당 번호의 수업이 없습니다.");
      return;
    }

    Lesson newLesson = new Lesson();
    //String inputStr = null; // input 값을 담을 문자열 변수 선언

    // 아무것도 변경안했으면 "변경을 취소했습니다." 출력하기 위해 change 값을 false로 초기화
    boolean changed = false;

    Lesson oldLesson = this.lessonList.get(index); // 추가

    newLesson.setNo(oldLesson.getNo());

    //String label = String.format("수업명?(%s)? ", oldLesson.getTitle());
    newLesson.setTitle(prompt.inputString(String.format("수업명(%s)? ", oldLesson.getTitle()),
        oldLesson.getTitle()));

    //    if (inputStr.equals("")) {
    //      // 빈 문자열이면(값이 없으면)= inputStr.length() == 0
    //      newLesson.setTitle(oldLesson.getTitle());
    //    } else {
    //      newLesson.setTitle(inputStr);
    //      changed = true;
    //    }

    newLesson.setDescription(prompt.inputString(String.format("수업내용(%s)? ", oldLesson.getDescription())));
//    System.out.print("수업내용? ");
//    inputStr = input.nextLine();
//    if (inputStr.equals("")) {
//      newLesson.setDescription(oldLesson.getDescription());
//    } else {
//      newLesson.setDescription(inputStr);
//      changed = true;
//    }
    //newLesson.setDescription(input.nextLine());

    newLesson.setStartDate(prompt.inputDate(String.format("시작일(%s)? ", oldLesson.getStartDate()),
        oldLesson.getStartDate()));
    
//    System.out.printf("시작일(%s)? ", oldLesson.getStartDate());
//    inputStr = input.nextLine();
//    if (inputStr.equals("")) {
//      newLesson.setStartDate(oldLesson.getStartDate());
//    } else {
//      newLesson.setStartDate(Date.valueOf(inputStr));
//      changed = true;
//    }
    //newLesson.setStartDate(Date.valueOf(input.next()));

    newLesson.setEndDate(prompt.inputDate(String.format("종료일(%s)? ", oldLesson.getEndDate()),
        oldLesson.getEndDate()));
    
//    System.out.printf("종료일(%s)? ", oldLesson.getEndDate());
//    inputStr = input.nextLine();
//    if (inputStr.equals("")) {
//      newLesson.setEndDate(oldLesson.getEndDate());
//    } else {
//      newLesson.setEndDate(Date.valueOf(inputStr));
//      changed = true;
//      // 숫자로 작성된 문자열을 주면 그걸 숫자로 바꿔서 리턴해준다.
//    }
    //newLesson.setEndDate(Date.valueOf(input.next()));

    newLesson.setTotalHours(prompt.inputInt(String.format("총수업시간(%d)? ", oldLesson.getTotalHours()),
        oldLesson.getTotalHours()));
//    System.out.printf("총수업시간(%d)? ", oldLesson.getTotalHours());
//    inputStr = input.nextLine();
//    if (inputStr.equals("")) {
//      newLesson.setTotalHours(oldLesson.getTotalHours());
//    } else {
//      newLesson.setTotalHours(Integer.parseInt(inputStr));
//      changed = true;
//      // Integer.parseInt() 숫자로 작성된 문자열을 주면 그걸 숫자로 바꿔서 리턴해준다.
//    }
    //newLesson.setTotalHours(input.nextInt());
    
    newLesson.setDayHours(prompt.inputInt(String.format("일수업시간(%d)? ", oldLesson.getDayHours()),
        oldLesson.getDayHours()));
    /* 위 문장을 풀어 쓴 것
    int oldValue = oldLesson.getDayHours();
    String label = "일수업시간(" + oldValue + ")? ";
    int newValue = inputInt(label, oldValue);
    newLesson.setDayHours(newValue);
    */
//    System.out.printf("일수업시간(%d)? ", oldLesson.getDayHours());
//    inputStr = input.nextLine();
//    if (inputStr.equals("")) {
//      newLesson.setDayHours(oldLesson.getDayHours());
//    } else {
//      newLesson.setDayHours(Integer.parseInt(inputStr));
//      changed = true;
//      // 숫자로 작성된 문자열을 주면 그걸 숫자로 바꿔서 리턴해준다.
//    }
    
    
    //newLesson.setDayHours(input.nextInt());
    //input.nextLine();

    //this.lessonList.set(index, newLesson);
    // 배열[index]에 newLesson 객체를 복사
    if (oldLesson.equals(newLesson)) {
      // 인스턴스의 값이 같을 리 없다. 그렇기 때문에 무조건 false가 나와서
      // 빈 문자열을 줘도 아래 문장을 실행한다. -> equals 메서드의 오버라이딩 필요(Lesson 클래스로)
      System.out.println("수업 변경을 취소하였습니다.");
      return;
    }
    
    this.lessonList.set(index,  newLesson);
    System.out.println("수업을 변경했습니다.");
//    if (changed) { // 하나만 변경해도 true
//      this.lessonList.set(index, newLesson);
//      System.out.println("수업을 변경했습니다.");
//    } else {
//      System.out.println("수업 변경을 취소했습니다.");
//    }
  }

  public void deleteLesson() {
    //    System.out.println("번호? ");
    //    int no = input.nextInt();
    //    input.nextLine();
    //    int index = indexOfLesson(no);

    //Lesson lesson = this.lessonList.get(index);

    int index = indexOfLesson(prompt.inputInt("번호? "));

    if (index == -1) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
      return;
    }

    this.lessonList.remove(index);
    System.out.println("수업을 삭제했습니다.");
  }

  private int indexOfLesson(int no) {
    for (int i = 0; i < this.lessonList.size(); i++) {
      if (this.lessonList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
// com.eomcs.util 패키지의 Prompt 파일로 이동
//  private String inputString(String label) {
//    System.out.print(label);
//    return input.nextLine();
//  }
//
//  // inputString 오버로딩
//  private String inputString(String label, String defaultValue) {
//    System.out.print(label);
//    String value = input.nextLine();
//    if (value.length() == 0) { // 문자열이 없으면
//      return defaultValue; // 파라미터로 준 값
//    }
//    return value; // 사용자가 입력한 값
//  }
//
//  private int inputInt(String label) {
//    System.out.print(label);
//    return Integer.parseInt(input.nextLine()); // 입력값을 정수로 변환
//  }
//
//  // inputInt 오버로딩
//  private int inputInt(String label, int defaultValue) {
//    System.out.print(label);
//    String value = input.nextLine();
//    if (value.length() == 0) {
//      return defaultValue;
//    }
//    return Integer.parseInt(value);
//  }
//
//  private Date inputDate(String label) {
//    System.out.print(label);
//    return Date.valueOf(input.nextLine());
//  }
//
//  // inputDate 오버로딩
//  private Date inputDate(String label, Date defaultValue) {
//    System.out.print(label);
//    String value = input.nextLine();
//    if (value.length() == 0) {
//      return defaultValue;
//    }
//    return Date.valueOf(value);
//  }
}