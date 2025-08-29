package com.javarush.dementev.command;

public class BruteForce extends AbstractAction{
    public void run(String[] parameters) {
        String inFileName = parameters[0];
        String outFileName = parameters[1];
        copyWithKey(inFileName, outFileName, perfectBruteKey());
    }

    int perfectBruteKey(){
        return 0;
    }
}
