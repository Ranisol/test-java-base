package io.input_output_stream;


import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/** 문자 기반 입력 스트림 최상위 클래스
 * {@link FileReader}
 * {@link BufferedReader}
 * {@link java.io.InputStreamReader}
 * */
public class ReaderExample {
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

    @Test
    void readCbuf() {
        try(Reader reader = new FileReader(filePath)) {
            char[] cbufs = new char[5];
            reader.read(cbufs);
            System.out.println(cbufs);
        } catch (IOException e) { e.printStackTrace(); }
    }




}
