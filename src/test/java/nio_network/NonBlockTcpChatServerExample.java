package nio_network;



import java.io.IOException;

/**
 * 같은 기능을 하는 채팅서버 - io_network 의 ChatServerExample
 * - 연결 종료는 고려하지 않음
 * */

class MainServer {
    public static void main(String[] args) throws IOException {
        new NonBlockTcpChatServerExample().startServer();
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


class NonBlockTcpChatServerExample {
    void startServer() throws IOException {

    }
}


class MockClient {
    String clientName;

    MockClient(String name) {
        this.clientName = name;
    }

    void startClient() throws IOException {

    }
}