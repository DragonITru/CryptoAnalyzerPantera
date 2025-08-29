package com.javarush.dementev;

import com.javarush.dementev.command.ActionNameCommand;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class UserInput {
    private final String inUrl;
    private final String outUrl;
    private String key;
    private String nameCommand;


    public UserInput(Scanner scanner) {

        System.out.println("Введите путь к файлу источнику");
        inUrl = scanner.nextLine();
        System.out.println("Введите путь куда сохранить файл");
        outUrl = scanner.nextLine();
        System.out.println("""
                Что вы хотите сделать ?
                1. Зашифровать файл - введите ENCODE \n
                2. Расшифровать файл - введите DECODE \n
                3. Взломать с помощью словаря - введите BRUTEFORCE
                """);

        Set<String> validCommands = new HashSet<>();
        for (ActionNameCommand command : ActionNameCommand.values()) {
            validCommands.add(command.name());
        }

        while (true) {
            nameCommand = scanner.nextLine();
            String upperCommand = nameCommand.toUpperCase();

            if (validCommands.contains(upperCommand)) {
                ActionNameCommand actionNameCommand = ActionNameCommand.valueOf(upperCommand);
                switch (actionNameCommand) {
                    case ENCODE: {
                        nameCommand = "ENCODE";
                        System.out.println("Выбран режим - шифрование. \n " +
                                "Введите ключ шифрования:");
                        key = scanner.nextLine();
                        break;
                    }
                    case DECODE: {
                        nameCommand = "DECODE";
                        System.out.println("Выбран режим - расшифровка. \n " +
                                "Введите ключ расшифровки:");
                        key = scanner.nextLine();
                        break;
                    }
                    case BRUTEFORCE: {
                        nameCommand = "BRUTEFORCE";
                        System.out.println("Выбран режим - BruteForce.");
                        break;
                    }
                }
                break;
            }
            System.out.println("Неверная команда. Попробуйте снова");
        }
        System.out.println("Данные получены, начинаю работу");
    }

    public String[] getCommandLine() {
        return new String[]{inUrl, outUrl, key, nameCommand};
    }
}
