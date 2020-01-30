package com.eomcs.io.ex06;
// + Exam0310.java, Exam0320.java
import java.io.FileInputStream;
import java.io.IOException;

public class BufferedInputStream extends FileInputStream {
  // 상속을 이용하여 FileInputStream 클래스의 기존 기능을 확장!
  byte[] buf = new byte[8192];
  int size; // 배열에 저장되어 있는 바이트의 수
  int cursor; // 바이트 읽은 배열의 위치

  public BufferedInputStream(String filename) throws Exception {
    super(filename); // 파일명을 생성자로 받는 수퍼 클래스 호출
  }
  // 파일의 크기가 배열보다 큰데 buf 배열이 꽉 찼을 때 새 배열을 만드는 것이 아니다.
  // read(buf);로 남은 메모리만큼의 데이터만 읽어와서 size만큼 read()를 반복하여 기존 배열에 담는 것
  // 바이트 배열을 한 번에 받아 와서 앞의 1바이트를 리턴하는 메서드
  @Override
  public int read() throws IOException {
    if (cursor == size) { // 버퍼에 저장되어 있는 데이터를 모두 읽었다는 의미
      if ((size = super.read(buf)) == -1) { // 파일에서 데이터를 읽으려 했는데 데이터가 없다.
        return -1;
      }
      /* 위와 같은 코드다.
       if (true) {
       size = super.read(buf);
       if (size == -1) {
       return -1;
       }
       */
      cursor = 0;
    }
    return buf[cursor++] & 0x000000ff; // 앞의 3바이트 날리고 제대로 된 byte값으로 바꿔줌
  }

  // ex07.DataBufferedInputStream의 readUTF()에서 사용되는 read(byte[])를 오버라이딩하지 않으면
  // ex07.Exam0210, Exam0220의 입력 결과가 UTF-8로 바뀌어 나오지 않는다.
  @Override
  public int read(byte[] buf) throws IOException {
    int i = 0;
    for (; i < buf.length; i++) {
      // 파일에서 1바이트를 읽는다.
      int b = this.read();

      if (b == -1) {
        break; // 바이트 배열을 다 채우기도 전에 읽을 데이터가 없으면 반복문을 나간다.
      }

      buf[i] = (byte)b;
      // 파라미터로 받은 바이트 배열에 채운다.
    }
    return i; // 지금까지 읽은 데이터의 수
  }

  /* FileInputStream int read(byte[] b) 위의 메서드로 오버라이딩
      public int read(byte b[]) throws IOException {
        return readBytes(b, 0, b.length); // 바이트 배열에 담긴 총 바이트 수를 리턴
    }
   */
}