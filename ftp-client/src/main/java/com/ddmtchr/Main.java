package com.ddmtchr;

import com.ddmtchr.command.*;
import com.ddmtchr.util.ArgumentParser;
import com.ddmtchr.util.ConsoleWorker;
import com.ddmtchr.util.FTPWorker;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static boolean isRunning = true;
    private static final Map<String, Integer> commandsArgs = new HashMap<>();

    public static void main(String[] args) {
        String login = null, host = null;

        try {
            Map<String, String> argsMap = ArgumentParser.parseArguments(args);
            login = argsMap.get("login");
            host = argsMap.get("host");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        ConsoleWorker cw = new ConsoleWorker();
        System.out.print("Enter password: ");
        String password = cw.readPassword();

        System.out.println("Login: " + login);
        System.out.println("Host: " + host);

        String serverUrl = "ftp://" + login + ":" + password + "@" + host + ":21";

        try {
            System.out.println("Connecting...");
            FTPWorker client = new FTPWorker(serverUrl);
            System.out.println("Connected successfully!\n");

            commandsArgs.put("list", 0);
            commandsArgs.put("info", 1);
            commandsArgs.put("add", 1);
            commandsArgs.put("delete", 1);
            commandsArgs.put("exit", 0);
            Command help = new HelpCommand(client);
            help.execute();

            while (isRunning) {
                Command command;
                String[] userInput = cw.parseInput();
                String stringCommand = userInput[0];
                Integer argsNumber = commandsArgs.get(stringCommand);
                if (argsNumber == null) {
                    command = new HelpCommand(client);
                    command.execute();
                    continue;
                }
                if (argsNumber != userInput.length - 1) {
                    System.out.println("Wrong number of args for this command");
                    continue;
                }

                switch (stringCommand) {
                    case "list":
                        command = new ListCommand(client);
                        break;
                    case "info":
                        long id;
                        try {
                            id = Long.parseLong(userInput[1]);
                        } catch (NumberFormatException e) {
                            System.out.println("ID is number");
                            continue;
                        }
                        command = new InfoCommand(client, id);
                        break;
                    case "add":
                        String name = userInput[1];
                        command = new AddCommand(client, name);
                        break;
                    case "delete":
                        long idToDelete;
                        try {
                            idToDelete = Long.parseLong(userInput[1]);
                        } catch (NumberFormatException e) {
                            System.out.println("ID is number");
                            continue;
                        }
                        command = new DeleteCommand(client, idToDelete);
                        break;
                    case "exit":
                        command = new ExitCommand(client);
                        break;
                    default:
                        command = new HelpCommand(client);
                }
                command.execute();
            }

        } catch (MalformedURLException e) {
            System.out.println("Wrong URL");
        } catch (IOException e) {
            System.out.println("Wrong credentials or IP address");
        }
    }
}
