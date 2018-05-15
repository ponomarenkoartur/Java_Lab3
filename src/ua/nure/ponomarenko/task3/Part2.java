package ua.nure.ponomarenko.task3;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    public static final String FILE_PATH = "src/ua/nure/ponomarenko/task3/part2.txt";

    public static void main(String[] args) {
        String inputText = FileReader.readTextFromFile(FILE_PATH);
        String outputText = deleteWordsHavingCharacterRepetitions(inputText);
        System.out.println("Part2:\n" +
                "\tInput  data: " + inputText + "\n" +
                "\tOutput data: " + outputText);
    }

    public static String deleteWordsHavingCharacterRepetitions(String inputText) {
        String patternString = "\\S*(\\S)\\S*\\1\\S*";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(inputText);

        StringBuilder stringBuilder = new StringBuilder();

        while(matcher.find()) {
            stringBuilder.append(matcher.replaceAll(""));
        }

        return stringBuilder.toString();
    }
}
