package io.reader_writer;

import org.junit.jupiter.api.Test;

import java.io.*;

class FileReaderExample {
    private String filePath = getClass().getClassLoader().getResource("output.txt").getPath();

    @Test
    void createFileReader() throws FileNotFoundException {
        FileReader fr1 = new FileReader(filePath);

        // 파일로 만들기
        File file = new File("out/test/resources/output.txt");
        FileReader fr2 = new FileReader(file);
    }

    // FileinputStream 과 다르게 문자로 바로 읽어들일 수 있음
    @Test
    void readFileReader() throws IOException {
        FileReader fr = new FileReader(filePath);
        char[] cbuf = new char[10];
        int readCharLen;
        while ((readCharLen = fr.read(cbuf)) != -1) {
            System.out.println(cbuf);
        }
        fr.close();
    }
}

class FileWriterExample {
    private String filePath = getClass().getClassLoader().getResource("output.txt").getPath();

    @Test
    void createFileWriter() throws IOException {
        FileWriter fw = new FileWriter(filePath);

        // 파일로 만들기
        File file = new File("out/test/resources/output.txt");
        FileWriter fw2 = new FileWriter(file);
    }

    // writer.write 들어온 모든 바이트를 출력 스트림으로 내보냄 (버퍼에 쌓임), 이전에 있던 애 덮음
    // writer.flush 버퍼에 잔류하는 모든 문자열 출력, 버퍼 비워냄
    // writer.close // 시스템 자원 반환
    @Test
    void writeFileWriter() {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write("문자열 바로 쓰기 가능");
            fw.append("테스트");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

