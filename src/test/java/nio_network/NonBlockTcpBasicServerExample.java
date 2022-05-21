package nio_network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Executors;

public class NonBlockTcpBasicServerExample {

    AsynchronousChannelGroup asynchronousChannelGroup;

    void createChannelGroup() throws IOException {
        asynchronousChannelGroup = AsynchronousChannelGroup.withFixedThreadPool(
                Runtime.getRuntime().availableProcessors(),
                Executors.defaultThreadFactory()
        );
    }

    void shutDownChannelGroup() throws IOException {
        asynchronousChannelGroup.shutdown();
        asynchronousChannelGroup.shutdownNow();
    }

    // 서버측에서 채널을 생성하고 소켓을 얻음
    void serverAsyncChannelMethods() throws IOException {
        // 기본 비동기 채널 그룹 얻음
        //AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();

        // 특정 그룹에 속한 채널 그룹 얻음
        AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open(asynchronousChannelGroup);

        // 소켓을 얻음
        serverSocketChannel.accept(null,
                new CompletionHandler<AsynchronousSocketChannel, Object>() {

                    @Override
                    public void completed(AsynchronousSocketChannel result, Object attachment) {
                        //result.read()
                        //result.write()

                        // 완료한 후 재호출로 계속 연결
                        serverSocketChannel.accept(null, this);
                    }

                    @Override
                    public void failed(Throwable exc, Object attachment) {

                    }
                }
        );

        // 채널에 포트 바인딩
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8080));

        // 채널 닫기
        serverSocketChannel.close();
    }

    // 클라이언트 측에서 채널을 생성하고 서버에 연결
    void clientAsyncChannelMethod() throws IOException {

        AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();

        channel.connect(
                new InetSocketAddress("localhost", 8080), null,
                new CompletionHandler<Void, Void>() {

                    @Override
                    public void completed(Void result, Void attachment) {}

                    @Override
                    public void failed(Throwable exc, Void attachment) {}
                }
        );
    }




}
