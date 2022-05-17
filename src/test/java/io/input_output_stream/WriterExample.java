package io.input_output_stream;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/** 문자 기반 출력 스트림 최상위 클래스
 * {@link java.io.FileWriter}
 * {@link java.io.BufferedWriter}
 * {@link java.io.PrintWriter}
 * {@link java.io.OutputStreamWriter}
 * */
public class WriterExample {
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

    @Test
    void writeCbuf() {
        try(Writer writer = new FileWriter(filePath)) {
            char[] data = "테스트한글".toCharArray();
            writer.write(data);
        } catch (IOException e) { e.printStackTrace(); }
    }
}
