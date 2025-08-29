package com.javarush.dementev;

import java.util.HashMap;

public class Constant {
    private static final String rus = "ЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮЁ";
    private static final String eng = "QWERTYUIOPASDFGHJKLZXCVBNM";
    private static final String numbers = "1234567890";
    private static final String chars = "!@#$%^&*()_+-=[];',./\\{} \"?";
    private final static String ALPHABET = rus + rus.toLowerCase() + eng + eng.toLowerCase() + numbers + chars;
    public static final HashMap charsMap = new HashMap();
    public static char[] indexAlphabet = ALPHABET.toCharArray();

    static {
        for (int i = 0; i < indexAlphabet.length; i++) {
            charsMap.put(indexAlphabet[i], i);
        }
    }
}
