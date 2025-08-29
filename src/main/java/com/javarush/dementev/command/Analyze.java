package com.javarush.dementev.command;
import com.javarush.dementev.Constant;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

//МЕТОД НАПИСАН НЕЙРОСЕТЬЮ giga.chat чисто ради эксперимента, сам не писал.
public class Analyze extends AbstractAction {
    private final static Map<Character, Double> FREQUENCY_PROFILE_RU = new HashMap<>();

    // Профиль частоты русских букв
    static {
        FREQUENCY_PROFILE_RU.put(' ', 0.18);   // Пробел часто встречается
        FREQUENCY_PROFILE_RU.put('о', 0.11);   // Наиболее распространённая буква
        FREQUENCY_PROFILE_RU.put('е', 0.08);
        FREQUENCY_PROFILE_RU.put('а', 0.07);
        FREQUENCY_PROFILE_RU.put('и', 0.07);
        FREQUENCY_PROFILE_RU.put('н', 0.06);
        FREQUENCY_PROFILE_RU.put('т', 0.06);
        FREQUENCY_PROFILE_RU.put('с', 0.05);
        FREQUENCY_PROFILE_RU.put('р', 0.04);
        FREQUENCY_PROFILE_RU.put('в', 0.04);
        // Добавьте остальные буквы русского алфавита...
    }

    String inFileName;

    @Override
    public void run(String[] parameters) {
        inFileName = parameters[0];
        String outFileName = parameters[1];
        runAction(inFileName, outFileName, analyzeText());
    }

    /**
     * Метод анализирует файл и вычисляет наиболее вероятный ключ дешифровки,
     * используя частотный анализ символов.
     */
    int analyzeText() {
        double minDifference = Double.MAX_VALUE;
        int bestShift = 0;

        Path pathIn = generatePath(inFileName);
        int shiftLength = Constant.indexAlphabet.length;

        // Чтение файла и подсчет частот символов
        Map<Character, Integer> frequencies = calculateFrequencies(pathIn);

        // Анализируем каждый возможный сдвиг
        for (int shift = 0; shift < shiftLength; shift++) {
            double difference = computeFrequencyDifference(frequencies, shift);

            if (difference < minDifference) {
                minDifference = difference;
                bestShift = shift;
            }
        }

        return bestShift;
    }

    /**
     * Подсчет частот символов в файле.
     *
     * @param path путь к файлу
     * @return карта частот символов
     */
    private Map<Character, Integer> calculateFrequencies(Path path) {
        Map<Character, Integer> freqMap = new HashMap<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            int ch;
            while ((ch = reader.read()) != -1) {
                Character character = (char) ch;

                if (!freqMap.containsKey(character)) {
                    freqMap.put(character, 0);
                }
                freqMap.put(character, freqMap.get(character) + 1);
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка чтения файла", e);
        }

        return freqMap;
    }

    /**
     * Вычисление разницы частот между профилем языка и данным текстом при заданном сдвиге.
     *
     * @param frequencies map частот символов
     * @param shift       смещение алфавита
     * @return разница частот
     */
    private double computeFrequencyDifference(Map<Character, Integer> frequencies, int shift) {
        double totalDifference = 0.0;
        long fileSize = sumOfAllChars(frequencies);
        int shiftLength = Constant.indexAlphabet.length;

        for (Character key : frequencies.keySet()) {
            if (Constant.charsMap.containsKey(key)) {
                int index = (int) Constant.charsMap.get(key);
                int shiftedIndex = (index + shift + shiftLength) % shiftLength;
                char shiftedChar = Constant.indexAlphabet[shiftedIndex];

                double expectedFreq = FREQUENCY_PROFILE_RU.getOrDefault(shiftedChar, 0.0);
                double actualFreq = (double) frequencies.get(key) / fileSize;

                totalDifference += Math.pow(expectedFreq - actualFreq, 2);
            }
        }

        return totalDifference;
    }

    /**
     * Получаем общую сумму всех символов в файле.
     *
     * @param frequencies map частот символов
     * @return сумма всех символов
     */
    private long sumOfAllChars(Map<Character, Integer> frequencies) {
        return frequencies.values().stream()
                .mapToLong(Integer::longValue)
                .sum();
    }
}