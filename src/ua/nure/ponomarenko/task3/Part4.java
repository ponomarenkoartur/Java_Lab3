package ua.nure.ponomarenko.task3;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 {
    public static final String FILE_PATH = "src/ua/nure/ponomarenko/task3/part4.txt";

    public static void main(String[] args) {
        String inputText = FileReader.readTextFromFile(FILE_PATH);
        String outputText;

        System.out.println("Part4:\n" +
                "\tInput  data: " + inputText);

        System.out.print("Enter query (string, double, int, stop)\n\t");

        // Read from console
        Scanner scanner = new Scanner(System.in);
        String inputFromConsole = scanner.nextLine();

        while (!inputFromConsole.equals("stop")) {
            switch (inputFromConsole) {
                case "string":
                    outputText = applyRegex(inputText, "(?<=\\s|^)(\\d+[^0-9\\.\\s]+\\S*|[^0-9\\s\\.]+\\S*|\\.\\S+[^0-9\\s])(?=\\s|$)");
                    break;
                case "double":
                    outputText = applyRegex(inputText, "(?<=\\s|^)(\\.\\d+|\\d+\\.\\d*)(?=\\s|$)");
                    break;
                case "int":
                    outputText = applyRegex(inputText, "(?<=\\s|^)\\d+(?=\\s|$)");
                    break;
                default:
                    outputText = "Command is not valid. Enter 'stop' to finish.";
            }
            System.out.println("\tOutput data: " + outputText);
            System.out.print("Enter query (string, double, int, stop)\n\t");
            inputFromConsole = scanner.nextLine();
        }
    }

    public static String applyRegex(String inputText, String regexPatternString) {
        StringBuilder stringBuilder = new StringBuilder();

        Pattern pattern = Pattern.compile(regexPatternString);
        Matcher matcher = pattern.matcher(inputText);

        while(matcher.find()) {
            stringBuilder.append(matcher.group());
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }
}