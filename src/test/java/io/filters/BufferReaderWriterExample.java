package io.filters;


import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * BufferedInputStream와 원리 동일
 * */
class BufferedReaderExample {

    // BufferedInputStream과 다르게 readLine 메서드를 추가로 가지고 있음. 이용할 시, \n \r로 구분된 행단위 문자열 한꺼번에 읽음
    @Test
    void run() throws IOException {
        Reader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        br.readLine();
    }

}

/**
 * BufferedOutputStream과 동일
 * */
class BufferedWriterExample {
}