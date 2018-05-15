package ua.nure.ponomarenko.task3;

import java.util.regex.*;
import java.io.*;

public class Part1 {
    public static final String FILE_PATH = "src/ua/nure/ponomarenko/task3/part1.txt";

    public static void main(String[] args) {
        File file = new File(FILE_PATH);
        FileInputStream fileInputStream;
        BufferedInputStream bufferedInputStream;
        DataInputStream dataInputStream;

        try {
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            dataInputStream = new DataInputStream(bufferedInputStream);

            while (dataInputStream.available() != 0) {
                String caseInvertedLine = invertCaseInWordsWhereMoreThanThreeLetters(dataInputStream.readLine());
                System.out.println(caseInvertedLine );
            }

            fileInputStream.close();
            bufferedInputStream.close();
            dataInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String invertCaseInWordsWhereMoreThanThreeLetters(String inputText) {
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
