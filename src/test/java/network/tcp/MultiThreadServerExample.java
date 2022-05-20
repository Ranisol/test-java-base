package network.tcp;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MultiThreadServerExample {

    ExecutorService executorService;
    ServerSocket serverSocket;
    List<MultiThreadClientExample> clients = Collections.emptyList();

    void startServer() throws IOException {
        executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 8080));

        } catch (IOException e) {
            assert serverSocket != null;
            if(!serverSocket.isClosed()) {
                stopServer();
            }
            e.printStackTrace();
        }
    }

    void acceptConnect() {
        executorService.submit(() -> {
            while (true) {
                Socket socket = serverSocket.accept();
                MultiThreadClientExample client = new MultiThreadClientExample(socket, executorService, clients);
                clients.add(client);
            }
        });
    }

    void stopServer() throws IOException {
        Iterator<MultiThreadClientExample> iterator = clients.iterator();
        while (iterator.hasNext()) {
            if(serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                executorService.shutdown();
            }
        }
    }
}

class MultiThreadClientExample{
    Socket socket;
    ExecutorService executorService;
    List<MultiThreadClientExample> otherClients;
    MultiThreadClientExample(Socket socket, ExecutorService executorService, List<MultiThreadClientExample> otherClients) throws IOException {
        this.socket = socket;
        this.executorService = executorService;
        this.otherClients = otherClients;
        receive();
    }
    void receive() throws IOException{
        while (true) {
            byte[] bytes = new byte[100];
            InputStream is = new BufferedInputStream(socket.getInputStream());
            int readByteCount = is.read(bytes);

            // 정상 close
            if(readByteCount == -1) { throw new IOException(); }

            String data = new String(bytes, 0, readByteCount, "UTF-8");
            for(MultiThreadClientExample other: otherClients) {
                other.send(data);
            }
        }
    }

    void send(String data) throws IOException, UnsupportedEncodingException {
        executorService.submit(() -> {
            try {
                byte[] bytes = data.getBytes("UTF-8");
                OutputStream os = new BufferedOutputStream(socket.getOutputStream());
                os.write(bytes);
                os.flush();
            } catch (Exception e) {try {socket.close();} catch (IOException ex) {ex.printStackTrace();}}
        });
    }
}