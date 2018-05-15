package ua.nure.ponomarenko.task3;

import java.util.regex.*;
import java.io.*;

public class Part1 {
    public static final String FILE_PATH = "src/ua/nure/ponomarenko/task3/part1.txt";

    public static void main(String[] args) {
        String inputText = FileReader.readTextFromFile(FILE_PATH);
        String outputText = invertCaseInWordsWhereMoreThanThreeLetters(inputText);
        System.out.println("Part1:\n" +
                "\tInput  data: " + inputText + "\n" +
                "\tOutput data: " + outputText);
    }

    public static String invertCaseInWordsWhereMoreThanThreeLetters(String inputText) {
        // TODO: когда в конце слова с меньшим количеством букв чем 3, то они вообще не записываються

        String patternString = "[a-zA-Z]{3,}";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(inputText);

        StringBuilder stringBuilder = new StringBuilder();

        while(matcher.find()) {
            matcher.appendReplacement(stringBuilder,
                    invertStringCase(matcher.group()));
        }

        return stringBuilder.toString();
    }

    public static String invertStringCase(String inputString) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Character character: inputString.toCharArray()) {
            Character invertedCharacter;
            if (Character.isUpperCase(character)) {
                invertedCharacter = Character.toLowerCase(character);
            } else {
                invertedCharacter = Character.toUpperCase(character);
            }
            stringBuilder.append(invertedCharacter);
        }
        return stringBuilder.toString();
    }
}
