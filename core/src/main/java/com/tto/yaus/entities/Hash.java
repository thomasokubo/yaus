package com.tto.yaus.entities;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class Hash {

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = UPPER.toLowerCase(Locale.ROOT);
    private static final String DIGITS = "0123456789";
    private static final String ALPHANUM = UPPER + LOWER + DIGITS;

    private Random random;
    private char[] buffer;
    private char[] symbols;

    public Hash(int length, final Random random, final String symbols) {
        if (length < 1) {
            throw new IllegalArgumentException("Length must be greater than 1");
        }
        if (symbols.length() < 2) {
            throw new IllegalArgumentException("Symbols must be greater than 2");
        }

        this.buffer = new char[length];
        this.symbols = symbols.toCharArray();
        this.random = Objects.requireNonNull(random);
    }

    public Hash(int length, final Random random) {
        this(length, random, ALPHANUM);
    }

    public Hash(int length) {
        this(length, new SecureRandom());
    }

    public String generate() {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = symbols[random.nextInt(symbols.length)];
        }

        return new String(buffer);
    }
}
