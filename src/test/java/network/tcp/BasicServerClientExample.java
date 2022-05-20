package network.tcp;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

class BasicServerExample {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 8080));
            while (true) {
                System.out.println("연결 대기");
                Socket socket = serverSocket.accept();
                InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                System.out.println("연결 수락" + isa.getHostName());
            }

//            if(!serverSocket.isClosed()) {
//                serverSocket.close();
//            }
        } catch (Exception e){e.printStackTrace();}


    }
}

class BasicClientExample {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket();
            System.out.println("연결 요청");
            socket.connect(new InetSocketAddress("localhost", 8080));
            System.out.println("연결 성공");
            if(!socket.isClosed()) {
                socket.close();
            }
        } catch (Exception e) {e.printStackTrace();}

    }
}
