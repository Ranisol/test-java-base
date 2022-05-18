package io.io_stream;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 바이트 기반 입력 스트림의 최상위 클래스, 단지 바이트 기반 데이터를 다루며 데이터 종류에 따른 어떤 번역도 제공하지 않음
 * {@link java.io.FileInputStream}
 * {@link java.io.BufferedInputStream}
 * {@link java.io.DataInputStream}
 * */
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

    // is.read 읽어들인 부분은 없어지고, 더이상 읽을게 없으면 -1 리턴
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


/**
 * 바이트 기반 출력 스트림의 최상위 클래스, 단지 바이트 기반 데이터를 다루며 데이터 종류에 따른 어떤 번역도 제공하지 않음
 * {@link java.io.FileOutputStream}
 * {@link java.io.PrintStream}
 * {@link java.io.BufferedOutputStream}
 * {@link java.io.DataOutputStream}
 * */
class OutputStreamExample {
    // out 에 있는 애에 적용
    private String filePath = getClass().getClassLoader().getResource("test-image.png").getFile();

    @Test
        // 주어진 int 값에서 끝에 있는 1 바이트만 출력 스트림으로 보낸다. 4바이트 전부를 보내지 않음
    void writeFile() {
        try(OutputStream os = new FileOutputStream(filePath);) {
            os.write(2000); // 마지막 1 바이트만 보낸다.
        }catch (IOException e) { e.printStackTrace(); }
    }

    // os.write 들어온 모든 바이트를 출력 스트림으로 내보냄 (버퍼에 쌓임), 이전에 있던애 덮음
    // os.flush 버퍼에 잔류하는 모든 문자열 출력, 버퍼 비워냄
    // os.close // 시스템 자원 반환
    @Test
    void writeFileWithBytes(){
        try(OutputStream os = new FileOutputStream(filePath);) {
            byte [] data = "123".getBytes();
            for (byte datum : data) {
                System.out.println(datum);
            }
            os.write(data);
            os.flush();
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
