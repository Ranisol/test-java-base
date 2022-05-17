package io.input_output_stream;

import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamExample {
    private String filePath = getClass().getClassLoader().getResource("output.txt").getFile();

    @Test
    // 주어진 int 값에서 끝에 있는 1 바이트만 출력 스트림으로 보낸다. 4바이트 전부를 보내지 않음
    void writeFile() {
        try(OutputStream os = new FileOutputStream(filePath);) {
            os.write(2000); // 마지막 1 바이트만 보낸다.
        }catch (IOException e) { e.printStackTrace(); }
    }

    @Test
    void writeFileWithBytes(){
        try(OutputStream os = new FileOutputStream(filePath);) {
            byte [] data = "123".getBytes();
            for (byte datum : data) {
                System.out.println(datum);
            }
            os.write(data);
        }catch (IOException e) { e.printStackTrace(); }
    }

    @Test
    void writeFileWithBytesAndArgs(){
        try(OutputStream os = new FileOutputStream(filePath);) {
            byte [] data = "123".getBytes();
            for (byte datum : data) {
                System.out.println(datum);
            }
            os.write(data, 1, 2);
        }catch (IOException e) { e.printStackTrace(); }
    }

    @Test
    void flush(){
        try(OutputStream os = new FileOutputStream(filePath);) {
            byte [] data = "123".getBytes();
            for (byte datum : data) {
                System.out.println(datum);
            }
            os.write(data, 1, 2);
            os.flush(); // 버퍼에 잔류하는 데이터를 모두 출력시킨다.
        }catch (IOException e) { e.printStackTrace(); }
    }

}
