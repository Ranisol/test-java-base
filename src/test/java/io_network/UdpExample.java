package io_network;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * udp: 비연결 지향적 프로토콜, 발신자가 일방적으로 데이터를 발신하는 방식
 * - 결과적으로 tcp보다는 빠르지만 데이터의 신뢰성은 떨어짐
 * - 중간에 패킷 좀 잃어버려도 괜찮은 대신 속도가 중요한 곳에서 씌임 (fps)
 * */

 class UdpSendExample {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();
        byte[] bytes = "데이터".getBytes(StandardCharsets.UTF_8);
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, new InetSocketAddress("localhost", 8080));
        datagramSocket.send(packet);
        datagramSocket.close();
    }
}

 class UdpReceiveExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        DatagramSocket datagramSocket = new DatagramSocket(8080);
        Thread thread = new Thread(() -> {
            try {
                DatagramPacket packet = new DatagramPacket(new byte[100], 100);
                datagramSocket.receive(packet);
                String data = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8);
            } catch (Exception e) {}
        });

        thread.start();
        Thread.sleep(10000);
        datagramSocket.close();
    }
}