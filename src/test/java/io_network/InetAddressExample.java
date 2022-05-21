package io_network;

import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressExample {
    @Test
    void getInfo() throws UnknownHostException {
        // 컴퓨터의 InetAddress 를 얻음
        // 로컬 컴퓨터의 ip 주소 및 도메인 이름을 DNS 에서 검색한 후 ip 주소를 가져오는 기능 제공
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(inetAddress.getHostAddress());

        // InetAddress inetAddress1 = InetAddress.getByName(""); // 외부 컴퓨터 도메인 이름
        InetAddress[] inetAddress1 = InetAddress.getAllByName("www.naver.com");
    }
}
