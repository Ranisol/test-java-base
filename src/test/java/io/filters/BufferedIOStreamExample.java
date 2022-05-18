package io.filters;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 바이트 입력 스트림에 연결되어 버퍼를 제공해줌
 * - 버퍼는 입력 소스로부터 내부 버퍼의 크기만큼 미리 읽고 저장해둠
 * - read 시, 소스로부터 직접 읽는 대신 버퍼로부터 읽음
 * - 버퍼에는 최대 8192 문자가 저장될 수 있음
 * */
class BufferedInputStreamExample {
    private String filePath = getClass().getClassLoader().getResource("test-image.png").getPath();

    @Test
    void run() throws IOException {
        FileInputStream fis = new FileInputStream(filePath);

        FileInputStream buf_fis = new FileInputStream(filePath);
        InputStream is = new BufferedInputStream(buf_fis);

        long startFis = System.currentTimeMillis();
        while (fis.read() != -1){}
        long endFis = System.currentTimeMillis();
        System.out.println("사용 x 시: " + (endFis - startFis) + "ms");

        long startBufFis = System.currentTimeMillis();
        while (is.read() != -1){}
        long endBufFis = System.currentTimeMillis();
        System.out.println("사용 o 시: " + (endBufFis - startBufFis) + "ms");
    }
}

/**
 * 바이트 출력 스트림에 연결되어 버퍼를 제공해줌
 * - 버퍼는 출력 소스(프로그램)로부터 내부 버퍼 크기만큼 쌓아둠
 * - read시, 모든 데이터를 한번에 보냄
 * - 버퍼에는 최대 8192 문자가 저장될 수 있음
 * - 버퍼가 가득 찼을때만 출력하기 때문에 flush 호출해야함
 * */
class BufferedOutputStreamExample {
    private String filePath = getClass().getClassLoader().getResource("test-image.png").getPath();
    private String newFilePath = getClass().getClassLoader().getResource("test-image.png").getPath();


    @Test
    void run() throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        InputStream bis = new BufferedInputStream(fis);

        FileOutputStream fos = new FileOutputStream(newFilePath);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        int data;
        while ((data = bis.read()) != -1) {
            bos.write(data);
        }
        bos.flush();

    }
}