package network.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

class ProcessDataServerExample {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 8080));
            while (true) {
                // 연결
                System.out.println("연결 대기");
                Socket socket = serverSocket.accept();
                InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                System.out.println("연결 수락" + isa.getHostName());

                // 요청 데이터
                InputStream is = new BufferedInputStream(socket.getInputStream());
                byte[] isData = new byte[100];
                int readDataLen = is.read(isData);
                String message = new String(isData, 0,readDataLen, StandardCharsets.UTF_8);
                System.out.println("데이터 받기: " + message);

                // 응답
                OutputStream os = new BufferedOutputStream(socket.getOutputStream());
                byte[] returnMessage = "하이".getBytes(StandardCharsets.UTF_8);
                os.write(returnMessage);
                os.flush();
                System.out.println("데이터 보내기 성공");

                is.close();
                os.close();
                socket.close();
            }
        } catch (Exception e){e.printStackTrace();}
    }
}

class SendAndGetDataClientExample {
    public static void main(String[] args) {
        try {
            // 연결
            Socket socket = new Socket();
            System.out.println("연결 요청");
            socket.connect(new InetSocketAddress("localhost", 8080));
            System.out.println("연결 성공");

            // 요청
            OutputStream os = new BufferedOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            byte[] sendMessage = scanner.nextLine().getBytes("UTF-8");
            os.write(sendMessage);
            os.flush();
            System.out.println("보내기 성공");

            // 응답
            InputStream is = new BufferedInputStream(socket.getInputStream());
            byte[] bytes = new byte[100];
            int readByteCount = is.read(bytes);
            String message = new String(bytes, 0, readByteCount);
            System.out.println("데이터 받기: " + message);

            os.close();
            is.close();
            socket.close();

            if(!socket.isClosed()) {
                socket.close();
            }
        } catch (Exception e) {e.printStackTrace();}

    }
}
