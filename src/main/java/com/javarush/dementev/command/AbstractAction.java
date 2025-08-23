package com.javarush.dementev.command;
import com.javarush.dementev.Constant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;


public abstract class AbstractAction implements Action{
    public void copyWithKey(String inTXT, String outTXT, int key) {
        int indexChar;
        Path pathIN = Path.of(inTXT);
        Path pathOut = Path.of(outTXT);

        try (BufferedReader reader =  Files.newBufferedReader(pathIN);
             BufferedWriter writer =  Files.newBufferedWriter(pathOut)) {
            int lengthAlphabet = Constant.INDEXALPHABET.length;
            while ((indexChar = reader.read()) > -1) {
                char character = (char) indexChar;
                if (Constant.CHARSMAP.containsKey(character)) {
                    int index = (int) Constant.CHARSMAP.get(character);
                    index = (index + key + Math.abs(key) * lengthAlphabet) % lengthAlphabet;
                    writer.write(Constant.INDEXALPHABET[index]);
                } else if (character == '\n') {
                    writer.write(character);
                }
            }

        } catch (Exception e) {
// заглушка
        }
    }


}
