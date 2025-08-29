package com.javarush.dementev.command;

import com.javarush.dementev.Constant;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class BruteForce extends AbstractAction {
    String inFileName;

    public void run(String[] parameters) {
        inFileName = parameters[0];
        String outFileName = parameters[1];
        runAction(inFileName, outFileName, perfectBruteKey());
    }

    int perfectBruteKey() {
        int bestKey = 0;
        int maxCountUpperChar = 0;
        char searchKey1 = ' ';


        Path pathIN = generatePath(inFileName);
        int indexChar;
        int lengthAlphabet = Constant.indexAlphabet.length;
        for (int i = 0; i < lengthAlphabet; i++) {
            int countUpperChar = 0;
            try (BufferedReader reader = Files.newBufferedReader(pathIN)) {
                while ((indexChar = reader.read()) > -1) {
                    char character = (char) indexChar;

                    if (Constant.charsMap.containsKey(character)) {
                        int index = (int) Constant.charsMap.get(character);
                        index = (index + i + Math.abs(i) * lengthAlphabet) % lengthAlphabet;
                        if (searchKey1 == Constant.indexAlphabet[index]) {
                            countUpperChar++;
                        }
                    }
                }
            } catch (Exception e) {
// заглушка
            }
            if (countUpperChar > maxCountUpperChar) {
                maxCountUpperChar = countUpperChar;
                bestKey = i;
            }
        }
        return bestKey;
    }
}
