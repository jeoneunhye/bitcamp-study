package com.eomcs.io.ex07;
// + Exam0130.java
import java.io.ByteArrayInputStream;

public class ByteArrayDataInputStream extends ByteArrayInputStream {
  // 바이트 배열을 다루는 ByteArrayInputStream 클래스를 상속받아
  // ex05의 DataInputStream의 기능을 그대로 복제했다.
  public ByteArrayDataInputStream(byte[] buf) {
    super(buf); // 바이트 배열을 읽어오는 ByteArrayInputStream 생성자를 호출
  }

  public String readUTF() throws Exception {
    int size = this.read();
    byte[] bytes = new byte[size];
    this.read(bytes, 0, size); // 배열 갯수만큼 바이트를 읽어 배열에 저장한다.
    return new String(bytes, "UTF-8");
  }

  public int readInt() throws Exception {
    int value = 0;

    value = this.read() << 24;
    value += this.read() << 16;
    value += this.read() << 8;
    value += this.read();
    return value;
  }

  public long readLong() throws Exception {
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
    if (this.read() == 1)
      return true;
    else
      return false;
  }
}