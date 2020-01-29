// FileInputStream 활용 - JPEG 파일 읽기
package com.eomcs.io.ex02;

import java.io.File;
import java.io.FileInputStream;

public class Exam0410 {
  public static void main(String[] args) throws Exception {
    // 1) 읽을 파일의 정보를 준비한다.
    File file = new File("sample/photo1.jpg");

    // 2) 파일을 읽을 도구를 준비한다.
    FileInputStream in = new FileInputStream(file);
    // String이 아닌 File 객체를 파라미터로 직접 받은 것

    // => SOI(Start of Image) Segment 2바이트 읽기: JPEG는 항상 FF D8로 시작한다.
    int b1 = in.read(); // 00 00 00 ff
    int b2 = in.read(); // 00 00 00 d8
    int soi = b1 << 8 | b2; // b1을 8비트(1바이트) 왼쪽 이동
    System.out.printf("SOI: %x\n", soi); // 00 00 ff d8
    //   00 00 00 ff <== b1
    //   00 00 ff 00 <== b1 << 8
    // | 00 00 00 d8 <== b2
    // ------------------
    //   00 00 ff d8

    // => JFIF-APP0 segment Marker 2바이트 읽기
    int jfifApp0Marker = in.read() << 8 | in.read();
    System.out.printf("JFIF APP0 Marker: %x\n", jfifApp0Marker); // ff e0

    // => JFIF-APP0 Length 2바이트 읽기
    int jfifApp0Length = in.read() << 8 | in.read();
    System.out.printf("JFIF APP0 정보 길이: %d\n", jfifApp0Length); // 16(10진수로 표현 %d)

    // => JFIF-APP0 정보: 16바이트(위에서 알아낸 길이)
    byte[] jfifApp0Info = new byte[jfifApp0Length];
    in.read(jfifApp0Info);

    // => JFIF-APP0 Identifier: 5바이트 4A 46 49 46 00로 고정
    String jfifApp0Id = new String(jfifApp0Info, 0, 4); // 마지막 00은 c언어를 위한 것이라 제외함
    System.out.printf("JFIF APP0 ID: %s\n", jfifApp0Id); // JFIF

    // => SOF(Star of Frame) 정보 읽기
    // 그림 이미지의 크기 및 샘플링에 관한 정보를 보관하고 있다.
    // 항상 0xFFC0 ~ 0xFFC2로 표시한다.

    // => SOF Marker 찾기
    int b;
    while (true) {
      b = in.read();
      if (b == -1) {
        break; // 파일 끝에 도달
      }

      if (b == 0xFF) {
        b = in.read();
        if (b == -1) {
          break; // 파일 끝에 도달
        }

        if (b >= 0xC0 && b <= 0xC2) { // 0xC0 ~ 0xC2
          break; // 값을 찾았으므로 반복문 멈춤
        }
      }
    }

    if (b == -1) {
      System.out.println("유효한 JPEG 파일이 아닙니다.");
      in.close();
      return; // main() 종료
    }

    // FFC0 ~ FFC2 값을 찾고 나서 그 다음 값인
    // => SOF Length 2바이트 읽기
    int sofLength = in.read() << 8 | in.read();
    System.out.printf("SOF 데이터 크기: %d\n", sofLength); // 17

    // => SOF 데이터 읽기: 17바이트(위에서 알아낸 크기)
    byte[] sofData = new byte[sofLength];
    in.read(sofData);

    // => SOF 샘플링 정밀도: 1바이트
    System.out.printf("SOF 샘플링 정밀도: %d\n", sofData[0]); // 8

    // => SOF 이미지 높이: 2바이트
    int height = ((sofData[1] << 8) & 0xff00) | (sofData[2] & 0xff);

    // => SOF 이미지 너비: 2바이트
    int width = ((sofData[3] << 8) & 0xff00) | (sofData[4] & 0xff);
    System.out.printf("SOF 이미지 크기(w x h): %d x %d\n", width, height); // 4032 x 3024

    // 3) 읽기 도구를 닫는다.
    in.close();
  }
}