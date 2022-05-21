package io_network;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class MainServer {
    public static void main(String[] args) throws IOException {
        new ChatServerExample().startServer();
    }
}

class Client1 {
    public static void main(String[] args) throws IOException {
        new MockClient("클라").startClient();
    }
}

class Client2 {
    public static void main(String[] args) throws IOException {
        new MockClient("이름").startClient();
    }
}

class Client3 {
    public static void main(String[] args) throws IOException {
        new MockClient("누구").startClient();
    }
}

class ChatServerExample {

    ExecutorService executorService;
    ServerSocket serverSocket;
    List<Connect> connects = new ArrayList<>(10);

    void startServer() throws IOException {
        executorService = Executors.newFixedThreadPool(10);

        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("localhost", 8080));
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("연결 발생 " + socket.toString());
            Connect connect = new Connect(socket);
            this.connects.add(connect);
        }

    }

    class Connect{
        Socket socket;
        Connect(Socket socket) {
            this.socket = socket;
            // 특정 소켓에 대한 연결 유지
            executorService.submit(() -> {
                while (true) {
                    byte[] bytes = new byte[100];
                    InputStream is = socket.getInputStream();
                    int readByteCount = is.read(bytes);

                    // 정상 close
                    if(readByteCount == -1) { throw new IOException(); }

                    String data = new String(bytes, 0, readByteCount, StandardCharsets.UTF_8);
                    System.out.println("요청 처리: " + socket.getRemoteSocketAddress() + " " + Thread.currentThread().getName());


                    // 다른 클라이언트로 데이터 보내기
                    for(Connect connect: connects) {
                        OutputStream os = connect.socket.getOutputStream();
                        os.write(data.getBytes(StandardCharsets.UTF_8));
                        os.flush();
                    }
                }
            });
        }
    }
}


class MockClient {

    String clientName;
    Socket socket;

    MockClient(String name) {
        this.clientName = name;
    }

    void startClient() throws IOException {
        socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 8080));

        // 보내기 스레드
        new Thread(() -> {
            System.out.println(Thread.currentThread().getThreadGroup() + "보내기 시작");
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String message = this.clientName + ": " + scanner.nextLine();
                try {
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write(message.getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 받기 스레드
        new Thread(() -> {
            System.out.println(Thread.currentThread().getThreadGroup() + "받기 시작");
            while (true) {
                try {
                    byte[] bytes = new byte[100];
                    InputStream inputStream = socket.getInputStream();
                    int readLen = inputStream.read(bytes);
                    if(readLen == -1) break;
                    String message = new String(bytes, 0, readLen, StandardCharsets.UTF_8);
                    System.out.println(message);
                } catch (IOException e) {e.printStackTrace();}
            }
        }).start();
    }
}
