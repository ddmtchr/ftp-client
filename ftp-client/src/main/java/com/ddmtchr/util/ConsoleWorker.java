package com.ddmtchr.util;

import java.io.Console;
import java.util.Scanner;

public class ConsoleWorker {
    private final Console console;
    private final Scanner scanner;

    public ConsoleWorker() {
        this.console = System.console();
        this.scanner = new Scanner(System.in);

    }

    public String readPassword() {
        if (console != null) return String.valueOf(console.readPassword());
        else return scanner.nextLine();
    }

    public String[] parseInput() {
        String input = scanner.nextLine();
        return input.split("\\s+");
    }

}
