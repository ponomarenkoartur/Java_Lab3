package ua.nure.ponomarenko.task3;


import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    public static final String FILE_PATH = "src/ua/nure/ponomarenko/task3/part2.txt";

    public static void main(String[] args) {
        String inputString = "abc abc dfkjgh abc abc df ba ba";
        System.out.println(deleteWordsRepeatingWords(inputString));
    }

    public static String deleteWordsRepeatingWords(String inputText) {
        String returnText = inputText;
        String patternString = "\\b(\\S+)\\s(?=(\\S*\\s)*\\1\\b)";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(inputText);

        while(matcher.find()) {
            String foundWord = (inputText.substring(matcher.start(), matcher.end() - 1));
            returnText = returnText.replaceAll(foundWord, "");
        }

        return deleteRepeatingSpaces(returnText).trim();
    }

    public static String deleteRepeatingSpaces(String inputText) {
        String patternString = "\\s{2,}";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(inputText);

        StringBuilder stringBuilder = new StringBuilder();

        while(matcher.find()) {
            matcher.appendReplacement(stringBuilder, " ");
        }

        return stringBuilder.toString();
    }
}
