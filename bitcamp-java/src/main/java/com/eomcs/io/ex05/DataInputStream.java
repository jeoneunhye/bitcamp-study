package com.eomcs.io.ex05;
// + Exam0220.java
import java.io.FileInputStream;

public class DataInputStream extends FileInputStream {
  // 상속을 이용하여 FileInputStream 클래스의 기존 기능을 확장!
  public DataInputStream(String filename) throws Exception {
    super(filename); // 파일명을 생성자로 받는 수퍼 클래스 호출
  }

  public String readUTF() throws Exception {
    // 상속받은 read() 메서드를 사용하여 문자열 출력
    int size = this.read();
    byte[] bytes = new byte[size];
    this.read(bytes, 0, size); // 이름 배열 갯수만큼 바이트를 읽어 배열에 저장한다.
    return new String(bytes, 0, size, "UTF-8");
  }

  public int readInt() throws Exception {
    // 상속받은 read() 메서드를 사용하여 int값 출력
    int value = 0;

    value = this.read() << 24;
    value += this.read() << 16;
    value += this.read() << 8;
    value += this.read();
    return value;
  }

  public long readLong() throws Exception {
    // 상속받은 read() 메서드를 사용하여 long값 출력
    long value = 0;
    value += (long)this.read() << 56;
    value += (long)this.read() << 48;
    value += (long)this.read() << 40;
    value += (long)this.read() << 32;
    value += (long)this.read() << 24;
    value += (long)this.read() << 16;
    value += (long)this.read() << 8;
    value += this.read();
    return value;
  }

  public boolean readBoolean() throws Exception {
    // 상속받은 read() 메서드를 사용하여 boolean값 출력
    if (this.read()==1)
      return true;
    else
      return false;
  }
}