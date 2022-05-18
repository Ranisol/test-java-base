package io.filters;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * print, println 메서드를 통해 출력할 수 있음
 * - PrintStream은 바이트 출력 스트림과 연결
 * - PrintWrite는 문자 출력 스트림과 연결
 * */
class PrintStreamExample {
    static String filePath = PrintStreamExample.class.getClassLoader().getResource("output.txt").getPath();


    public static void main (String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            PrintStream ps = new PrintStream(fos);

            ps.println("테스트");
            ps.println("테스트2");
            ps.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

