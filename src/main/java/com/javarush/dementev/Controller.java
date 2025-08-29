package com.javarush.dementev;

import com.javarush.dementev.command.Analyze;
import com.javarush.dementev.command.BruteForce;
import com.javarush.dementev.command.Decode;
import com.javarush.dementev.command.Encode;

public class Controller {


    public Controller(String[] userInput) {
        switch (userInput[3]){
            case "ENCODE" -> new Encode().run(userInput);
            case "DECODE" -> new Decode().run(userInput);
            case "BRUTEFORCE" -> new BruteForce().run(userInput);
            case "ANALYZE" -> new Analyze().run(userInput);
        }
    }
}
