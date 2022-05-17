package io.input_output_stream;


import java.util.Scanner;

// Console 과 다르게 기본 타입의 값을 콘솔로부터 바로 읽을 수 있음
public class ScannerExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String data = scanner.nextLine();
        System.out.println(data);

    }
}
