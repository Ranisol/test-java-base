package jaba_18_io.input_output_stream;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

class InputStreamExample {


    // 입력스트림으로부터 1바이트 읽고 4 바이트 int 타입으로 리턴한다.

    private String imagePath = getClass().getClassLoader().getResource("test-image.png").getFile();

    @Test
    void readFile() {
        try(InputStream is = new FileInputStream(imagePath)) {
            int readByte;
            while ((readByte = is.read()) != -1) {
                System.out.println(readByte);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void readFileWithByte() {
        try(InputStream is = new FileInputStream(imagePath)){ // autoClosable
            int readByteNo;
            byte[] readBytes = new byte[10000];
            while ((readByteNo = is.read(readBytes)) != -1) {
                System.out.println("readByteNo : " + readByteNo);
                System.out.println(readBytes[0]);
            }
        }
        catch (FileNotFoundException e) {}
        catch (IOException e) {}

    }

}
