package com.ddmtchr.util;

import java.util.HashMap;
import java.util.Map;

public class ArgumentParser {
    private static final String USAGE_MESSAGE = "Использование: java -jar app <login> <host>";

    public static Map<String, String> parseArguments(String[] args) {
        Map<String, String> argumentMap = new HashMap<>();
        if (args.length != 2) throw new IllegalArgumentException(USAGE_MESSAGE);
        argumentMap.put("login", args[0]);
        argumentMap.put("host", args[1]);

        return argumentMap;
    }
}
