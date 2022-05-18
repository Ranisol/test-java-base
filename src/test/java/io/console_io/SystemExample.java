package io.console_io;

import java.io.IOException;
import java.io.InputStream;

class SystemIn {
    void systemInExample()  {
        InputStream inputStream = System.in;
        try {
            byte[] data = new byte[100];
            int len = inputStream.read(data); // 앞부분부터 채워받음
            System.out.println(
                    new String(data, 0,
                        len // 끝 2바이트는 Enter 키에 해당하는 캐리지 리턴(13)과 라인 피드(10)이므로 제외
                    )
            );
        } catch (IOException e) { e.printStackTrace();}
    }
}


class Main {
    public static void main(String[] args) {
        new SystemIn().systemInExample();
    }
}