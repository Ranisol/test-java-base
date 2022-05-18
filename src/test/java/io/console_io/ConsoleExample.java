package io.console_io;

import java.io.Console;

public class ConsoleExample {
    public static void main(String[] args) {
        Console console = System.console();

        String data = console.readLine(); // 터미널 필요

        System.out.println(data);
    }
}
