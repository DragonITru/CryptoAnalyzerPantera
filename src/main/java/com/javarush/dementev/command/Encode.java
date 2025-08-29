package com.javarush.dementev.command;

public class Encode extends AbstractAction{
    @Override
    public void run(String[] parameters) {
        String inFileName = parameters[0];
        String outFileName = parameters[1];
        int key = Integer.parseInt(parameters[2]);
        copyWithKey(inFileName, outFileName, key);
    }
}
