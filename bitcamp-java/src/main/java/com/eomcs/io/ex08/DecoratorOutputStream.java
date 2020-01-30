package com.eomcs.io.ex08;

import java.io.IOException;
import java.io.OutputStream;

public abstract class DecoratorOutputStream extends OutputStream {
  OutputStream 연결된부품;

  public DecoratorOutputStream(OutputStream 부품) {
    // 장식품처럼 붙였다 뗐다 할 수 있는 기능을 수행하는 객체는
    // 다른 장식품에 붙일 수 있도록 생성자에서 그 객체를 받아야 한다.
    this.연결된부품 = 부품; // '부품'을 받아와 '연결된부품'에 저장하는 생성자
  }

  @Override
  public void write(int b) throws IOException {
    연결된부품.write(b);
  }

  @Override
  public void close() throws IOException {
    연결된부품.close();
  }
}