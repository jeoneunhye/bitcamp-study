package com.eomcs.io.ex07;
// + Exam0210, Exam0220.java
import com.eomcs.io.ex06.BufferedOutputStream;

public class DataBufferedOutputStream extends BufferedOutputStream {
  public DataBufferedOutputStream(String filename) throws Exception {
    super(filename); // 파일명을 생성자로 받는 수퍼 클래스 FileOutputStream를 호출
  }

  public void writeUTF(String str) throws Exception {
    // 상속받은 write() 메서드를 사용하여 '문자열' 출력
    byte[] bytes = str.getBytes("UTF-8");
    this.write(bytes.length);// 1바이트만큼을 이용해 다음에 올 데이터의 length를 알려줌
    this.write(bytes);
  }

  public void writeInt(int value) throws Exception {
    // 상속받은 write() 메서드를 사용하여 'int값' 출력
    this.write(value >> 24);
    this.write(value >> 16);
    this.write(value >> 8);
    this.write(value);
  }

  public void writeLong(long value) throws Exception {
    // 상속받은 write() 메서드를 사용하여 'long값' 출력
    this.write((int)(value >> 56));
    this.write((int)(value >> 48));
    this.write((int)(value >> 40));
    this.write((int)(value >> 32));
    this.write((int)(value >> 24));
    this.write((int)(value >> 16));
    this.write((int)(value >> 8));
    this.write((int)value);
  }

  public void writeBoolean(boolean value) throws Exception {
    // 상속받은 write() 메서드를 사용하여 'boolean값' 출력
    if (value)
      this.write(1);
    else
      this.write(0);
  }
}