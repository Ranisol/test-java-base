package io.filters;


import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 자바의 기본 데이터 타입(boolean, char, short..)를 지원하는 스트림
 * - 입력할 때의 순서와 출력할때의 순서가 같아야함
 * */

class DataInputStreamExample {
    private String filePath = getClass().getClassLoader().getResource("test-image.png").getPath();


    @Test
    void run() throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        BufferedInputStream bis = new BufferedInputStream(fis);
        DataInputStream dis = new DataInputStream(bis);

        int data;
        while ((data = dis.read()) != -1) {
            System.out.println(data);
        }
    }
}


class DataOutputStreamExample {
    private String filePath = getClass().getClassLoader().getResource("output.dat").getPath();
    @Test
    void run() throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        DataOutputStream dos = new DataOutputStream(bos);

        dos.writeUTF("테스트");
        dos.writeDouble(1.2);
        dos.writeDouble(3.5);
        dos.writeBoolean(true);

        dos.flush();
        dos.close();

        FileInputStream fis = new FileInputStream(filePath);
        BufferedInputStream bis = new BufferedInputStream(fis);
        DataInputStream dis = new DataInputStream(bis);
        String s = dis.readUTF();
        double dou = dis.readDouble();
        boolean bol = dis.readBoolean();
        double dou2 = dis.readDouble();
        System.out.println(s + dou + bol + dou2);


    }
}
