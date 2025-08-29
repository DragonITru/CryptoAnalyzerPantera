package com.javarush.dementev;

import java.util.HashMap;
import java.util.Map;

public class Constant {
    private static final String rus = "ЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮЁ";
    private static final String eng = "QWERTYUIOPASDFGHJKLZXCVBNM";
    private static final String numbers = "1234567890";
    private static final String znaki = "!@#$%^&*()_+-=[];',./\\{} \"?";
    private final static String ALPHABET = rus + rus.toLowerCase() + eng + eng.toLowerCase() + numbers + znaki;
    public static final Map CHARSMAP = new HashMap();
    public static final char[] INDEXALPHABET = ALPHABET.toCharArray();

    // Инициализируем мапу с индексами символов
    static {
        for (int i = 0; i < INDEXALPHABET.length; i++) {
            CHARSMAP.put(INDEXALPHABET[i], i);
        }
    }
}
