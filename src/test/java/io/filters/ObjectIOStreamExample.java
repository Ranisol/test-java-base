package io.filters;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 메모리에 생성된 객체를 파일 또는 네트워크로 출력 가능
 * - 바이트 기반 스트림으로 객체를 출력하기 위해서는 객체를 연속적인 바이트로 변경해야함(Serialize)
 * - 반면, 바이트 기반 스트림을 객체로 복원하는 일열 역직렬화(deserialize) 라고 함
 * - 쓸때와 동일한 순서로 읽어야 한다.
 * */

class ObjectIOStreamExample {
    private String filePath = getClass().getClassLoader().getResource("output.dat").getPath();

    @Test
    void run() throws IOException, ClassNotFoundException {
        OutputStream os = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(new File(filePath));
        oos.flush();
        oos.close();
        os.close();

        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        File s = (File) ois.readObject();
        ois.close();
        fis.close();

        System.out.println(s);

    }
}


