package com.javarush.dementev.command;
import com.javarush.dementev.Constant;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;


public abstract class AbstractAction implements Action{
    public void copyWithKey(String inTXT, String outTXT, int key) {
        int indexChar;
        Path pathIN = generatePath(inTXT);
        Path pathOut = generatePath(outTXT);

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

    public static Path generatePath(String filename) {
        String TXT_FOLDER = System.getProperty("user.dir") +
                File.separator +
                "text" +
                File.separator;
        Path path = Path.of(filename);
        return path.isAbsolute()
                ? path
                : Path.of(TXT_FOLDER + filename);
    }


}
