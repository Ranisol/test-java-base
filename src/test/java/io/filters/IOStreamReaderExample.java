package io.filters;


import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 바이트 입력 스트림을 문자 입력 스트림인 Reader로 변환시킨다.
 * */
class InputStreamReaderExample {

    @Test
    void run() throws IOException {
        InputStream is = System.in;
        Reader reader = new InputStreamReader(is);

        /** fileReader 도 InputStreamReader의 하위클래스
         * - 내부적으로 FileInputStream과 InputStreamReader을 연결함
         * */
        //Reader fileReader = new FileReader("");

        int readCharNo;
        char[] cbuf = new char[100];
        while ((readCharNo = reader.read(cbuf)) != -1) {
            String data = new String(cbuf, 0, readCharNo);
            System.out.println(data);
        }
        reader.close();
    }
}

/**
 * 바이트 출력 스트림을 문자 출력 스트림인 Writer로 변환시킨다.
 * */
class OutputStreamReaderExample {
    @Test
    void run() throws IOException {
        OutputStream fos = new FileOutputStream("");
        Writer writer = new OutputStreamWriter(fos);

        writer.write("데이터");
        writer.flush();
        writer.close();
    }

}