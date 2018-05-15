package ua.nure.ponomarenko.task3;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 {
    public static final String FILE_PATH = "src/ua/nure/ponomarenko/task3/part4.txt";
    public static String REGEX;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputFromConsole = scanner.nextLine();
        String inputText = "";

        // Read file
        File file = new File(FILE_PATH);
        FileInputStream fileInputStream;
        BufferedInputStream bufferedInputStream;
        DataInputStream dataInputStream;

        try {
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            dataInputStream = new DataInputStream(bufferedInputStream);

            StringBuilder stringBuilder = new StringBuilder();

            while (dataInputStream.available() != 0) {
                stringBuilder.append(dataInputStream.readLine() + "\n");
            }

            inputText = stringBuilder.toString();

            fileInputStream.close();
            bufferedInputStream.close();
            dataInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!inputFromConsole.equals("stop")) {
            switch (inputFromConsole) {
                case "string":
                    REGEX = "\\b\\w\\b";
                    break;
                case "double":
                    REGEX = "(?<=\\s|^)(\\.\\d+|\\d+\\.\\d*)(?=\\s|$)";
                    break;
                case "int":
                    REGEX = "\\b\\d+\\b";
                    break;
            }
            applyRegex(inputText);
        }
    }

    public static String applyRegex(String inputText) {
        StringBuilder stringBuilder = new StringBuilder();

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(inputText);

        while(matcher.find()) {
            stringBuilder.append(matcher.group());
        }

        return stringBuilder.toString();
    }
}