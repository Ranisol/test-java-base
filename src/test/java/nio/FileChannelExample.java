package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 파일 채널은 작업하는동안 블로킹이 일어난다.
 * */
class FileChannelExample {
    void createAndClose() throws IOException {
        // 파일 채널을 생성한다.
        FileChannel fileChannel = FileChannel.open(Paths.get(""), StandardOpenOption.CREATE, StandardOpenOption.READ);

        // 파일 채널을 닫는다.
        fileChannel.close();
    }

    void readAndWrite() throws IOException {
        FileChannel fileChannel = FileChannel.open(Paths.get(""), StandardOpenOption.WRITE, StandardOpenOption.READ);

        // 읽기
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        fileChannel.read(byteBuffer);
    }

}

/**
 * 작업하는동안 블로킹이 일어나지 않는다.
 * */
class AsyncFileChannelExample {
    void create() throws IOException {
        AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(Paths.get(""), StandardOpenOption.READ);
    }
}
