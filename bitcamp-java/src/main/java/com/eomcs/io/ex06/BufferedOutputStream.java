package com.eomcs.io.ex06;
// + Exam0320.java
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStream extends FileOutputStream {
  // 상속을 이용하여 FileOutputStream 클래스의 기존 기능을 확장!
  byte[] buf = new byte[8192];
  int cursor;

  public BufferedOutputStream(String filename) throws Exception {
    super(filename); // 생성자로 파일명을 받는 수퍼 클래스 호출
  }

  @Override
  public void write(int b) throws IOException {
    if (cursor == buf.length) { // write(b)를 반복해서 버퍼가 다 차면
      super.write(buf); // 버퍼 바이트 배열에 들어 있는 데이터를 파일에 한 번에 출력한다.
      // 수퍼 클래스의 오리지날 메서드 write() 호출
      cursor = 0; // 다시 커서를 초기화시킨다.
    }

    // 1바이트 출력하라고 하면 일단 버퍼에 저장할 것이다.
    buf[cursor++] = (byte)b;
  }
  // 버퍼가 다 차지 않으면 그만큼은 출력되지 않아 파일이 복사되지 않고 손상된다.
  // => 남은 빈 배열을 방출하는 flush() 메서드를 추가한다.

  @Override
  public void write(byte[] buf) throws IOException {
    for (byte b : buf) {
      this.write(b & 0x000000ff); // int값을 받는 메서드 정상적인 양수 값으로 만들기 위해 & 연산 사용
      // 위에 int값을 파라미터로 받는 write() 메서드 호출
    }
  }

  @Override
  public void close() throws IOException {
    this.flush(); // 아직 파일로 출력되지 않고 버퍼에 남아 있는 데이터를 출력한다.
    super.close(); // FileOutputStream의 close() 호출
  }

  @Override
  public void flush() throws IOException {
    if (cursor > 0) { // write()를 보면, 버퍼가 다 차지 않으면 커서는 0 이상이다.
      this.write(buf, 0, cursor);
      cursor = 0;
    }
  }
}