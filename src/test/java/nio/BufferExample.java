package nio;

import java.nio.ByteBuffer;

class BufferMethodExample {

    /**
     * 버퍼가 사용하는 메모리 위치에 따라 다르다.
     * - 넌다이렉트는 JVM 힙 메모리를 이용하고 생성이 빠르다.
     *  - 대신 버퍼 크기와 입출력 성능은 낮다.
     * - 다이렉트는 운영체제의 메모리를 사용해 버퍼가 느리다.
     *  - 대신 버퍼 크기가 크고 입출력 성능이 높다.
     * */
    void createBuffer() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        ByteBuffer byteBuffer1 = ByteBuffer.allocateDirect(10);
    }

    // 이미 생성된 자바 배열을 래핑해서 생성한다.
    void wrapBuffer() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[100]);
    }
}
