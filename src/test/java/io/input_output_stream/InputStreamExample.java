package io.input_output_stream;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class InputStreamExample {
    private String imagePath = getClass().getClassLoader().getResource("test-image.png").getFile();
    private String filePath = getClass().getClassLoader().getResource("output.txt").getFile();


    @Test
    void readFile() {
        try(InputStream is = new FileInputStream(imagePath)) {
            int readByte;
            // 입력스트림으로부터 1바이트 읽고 4 바이트 int 타입으로 리턴한다.
            while ((readByte = is.read()) != -1) {
                System.out.println(readByte);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void readFileWithBytes() {
        try(InputStream is = new FileInputStream(imagePath)){ // autoClosable
            int readByteNo;
            byte[] readBytes = new byte[10000];
            // 한번에 정해진 길이만큼 읽어들인다. 시간대신 공간차지
            while ((readByteNo = is.read(readBytes)) != -1) {
                for(int i = 0; i < readBytes.length; i++ ) {
                    System.out.println(readBytes[i]);
                }
            }
            // is.close(); 더이상 사용하지 않으면 메서드 호출로 시스템 자원 풀어줌
        } catch (IOException e) {}
    }

    @Test
    void readTextFile() {
        try(InputStream is = new FileInputStream(filePath)){ // autoClosable
            int readByteNo;
            byte[] readBytes = new byte[10];
            // 한번에 정해진 길이만큼 읽어들인다. 시간대신 공간차지
            while ((readByteNo = is.read(readBytes)) != -1) {
                for(int i = 0; i < readBytes.length; i++ ) {
                    System.out.println(readBytes[i]);
                }
            }
            System.out.println(new String(readBytes));
            // is.close(); 더이상 사용하지 않으면 메서드 호출로 시스템 자원 풀어줌
        } catch (IOException e) {}
    }

    @Test
    void readFileWithByteAndArgs() {
        try(InputStream is = new FileInputStream(imagePath)){ // autoClosable
            int readByteNo;
            byte[] readBytes = new byte[10];
            // 5부터 5개 읽어서 인덱스 5부터 저장
            readByteNo = is.read(readBytes, 5, 5);
            for(int i = 0; i < readBytes.length; i++ ) {
                System.out.println(readBytes[i]);
            }

            // is.close(); 더이상 사용하지 않으면 메서드 호출로 시스템 자원 풀어줌
        } catch (IOException e) {}

    }

}
