package com.eomcs.lms.handler;
// "/hello" 명령어 처리
import com.eomcs.util.Prompt;

public class HelloCommand implements Command {
  Prompt prompt;

  public HelloCommand(Prompt prompt) {
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    String name = prompt.inputString("이름? ");
    System.out.printf("%s님 반갑습니다!\n", name);
  }
}