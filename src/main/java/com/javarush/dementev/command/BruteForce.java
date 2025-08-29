package com.javarush.dementev.command;

public class BruteForce extends AbstractAction{
    public void run(String[] parameters) {
        String encryptedFilename = parameters[0];
        String decryptedFilename = parameters[1];
        int key = Integer.parseInt(parameters[2]);
        copyWithKey(encryptedFilename, decryptedFilename, -1 * key);
    }
}
