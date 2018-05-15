package ua.nure.ponomarenko.task3;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    public static final String FILE_PATH = "src/ua/nure/ponomarenko/task3/part2.txt";

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
                String convertedLine = deleteWordsHavingCharacterRepetitions(dataInputStream.readLine());
                System.out.println(convertedLine);
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
