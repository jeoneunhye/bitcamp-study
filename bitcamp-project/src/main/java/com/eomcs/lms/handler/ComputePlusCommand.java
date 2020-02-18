package com.eomcs.lms.handler;
// "/compute/plus" 명령어 처리
import com.eomcs.util.Prompt;

public class ComputePlusCommand implements Command {
  Prompt prompt;

  public ComputePlusCommand(Prompt prompt) {
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    int a = prompt.inputInt("수1? ");
    int b = prompt.inputInt("수2? ");

    System.out.printf("계산 결과는 %d입니다.\n", a + b);
  }
}