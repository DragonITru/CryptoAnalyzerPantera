package com.javarush.dementev;
import java.util.Scanner;


public class Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserInput userInput = new UserInput(scanner);
        Controller controller = new Controller(userInput.getCommandLine());

        }
    }

