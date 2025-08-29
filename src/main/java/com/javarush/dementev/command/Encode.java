package com.javarush.dementev.command;

public class Encode extends AbstractAction{
    @Override
    public void run(String[] parameters) {
        String encryptedFilename = parameters[0];
        String decryptedFilename = parameters[1];
        int key = Integer.parseInt(parameters[2]);
        copyWithKey(encryptedFilename, decryptedFilename, key);
    }
}
