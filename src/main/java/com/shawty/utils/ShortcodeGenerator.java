package com.shawty.utils;

import java.security.SecureRandom;
import java.util.Random;

// Data structure: StingBuilder
// Algorithm: Randomized algorithm - O(n) (n = code length)

public class ShortcodeGenerator {

    private static final String CHAR_SET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final int CODE_LENGTH = 6;

    private static final Random RANDOM = new SecureRandom();

    public static String generate() {
        StringBuilder sb = new StringBuilder(CODE_LENGTH);

        for (int i = 0; i < CODE_LENGTH; i++) {
            int ind = RANDOM.nextInt(CHAR_SET.length());
            sb.append(CHAR_SET.charAt(ind));
        }

        return sb.toString();
    }
}
