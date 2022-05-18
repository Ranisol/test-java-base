package io.reader_writer;

import org.junit.jupiter.api.Test;

import java.io.*;

/** 문자 기반 입력 스트림 최상위 클래스, 순전히 바이트 코드만 다루는 InputStream과 다르게 바이트 코드에 대한 문자(char) 번역을 제공
 * {@link FileReader}
 * {@link BufferedReader}
 * {@link java.io.InputStreamReader}
 * */
class ReaderExample {
    private String filePath = getClass().getClassLoader().getResource("output.txt").getPath();

    @Test
    void read() {
        // 2바이트 문자 읽고 4 바이트 int 타입 리턴,
        try(Reader reader = new FileReader(filePath);) {
            int data = reader.read();
            char charData = (char) reader.read();
            System.out.println(data);
            System.out.println(charData);
        } catch (IOException e) { e.printStackTrace(); }

    }

    // inputStream 마찬가지로 읽은 애들은 없어짐
    @Test
    void readCbuf() {
        try(Reader reader = new FileReader(filePath)) {
            char[] cbufs = new char[5];
            reader.read(cbufs);
            System.out.println(cbufs);
        } catch (IOException e) { e.printStackTrace(); }
    }
}

/** 문자 기반 출력 스트림 최상위 클래스, 순전히 바이트 코드만 다루는 OutputStream과 다르게 바이트 코드에 대한 문자(char) 번역을 제공
 * {@link java.io.FileWriter}
 * {@link java.io.BufferedWriter}
 * {@link java.io.PrintWriter}
 * {@link java.io.OutputStreamWriter}
 * */
class WriterExample {
    // out 에 있는 애에 적용
    private String filePath = getClass().getClassLoader().getResource("output.txt").getPath();

    @Test
    void write() {
        try(Writer writer = new FileWriter(filePath)) {
            char[] data = "테스트한글".toCharArray();
            for(int i = 0; i < data.length; i++ ) {
                writer.write(data[i]);
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    // writer.write 들어온 모든 바이트를 출력 스트림으로 내보냄 (버퍼에 쌓임), 이전에 있던 애 덮음
    // writer.flush 버퍼에 잔류하는 모든 문자열 출력, 버퍼 비워냄
    // writer.close // 시스템 자원 반환
    @Test
    void writeCbuf() {
        try(Writer writer = new FileWriter(filePath)) {
            char[] data = "테스트한글".toCharArray();
            writer.write(data);
            writer.flush();
        } catch (IOException e) { e.printStackTrace(); }
    }
}

