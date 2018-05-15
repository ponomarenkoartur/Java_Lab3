package ua.nure.ponomarenko.task3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part5 {
    public static final String FILE_PATH = "src/ua/nure/ponomarenko/task3/part5.txt";

    public static void main(String[] args) {
        String inputText = FileReader.readTextFromFile(FILE_PATH);
        String outputText1 = convert1(inputText);
        String outputText2 = convert2(inputText);
        String outputText3 = convert3(inputText);
        System.out.println("Part1:\n" +
                "\tInput   data: \n" + inputText + "\n" +
                "\tOutput1 data: \n" + outputText1 + "\n" +
                "\tOutput2 data: \n" + outputText2 + "\n" +
                "\tOutput3 data: \n" + outputText3);
    }

    public static String convert1(String input) {
        StringBuilder stringBuilder = new StringBuilder();

        Pattern loginOrEmailPattern = Pattern.compile("(^|(?<=\\n))[a-zA-Zа-яА-Я]+(?=;)|(?<=;)\\S+($|(?=\\n))");
        Matcher matcher = loginOrEmailPattern.matcher(input);

        // Find login or email by turn
        for (int i = 0; matcher.find(); i++) {
            stringBuilder.append(matcher.group());
            if (i % 2 == 0) {
                stringBuilder.append(" ==> ");
            } else {
                stringBuilder.append("\n");
            }
        }

        // Delete last '\n' character in the text
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    public static String convert2(String input) {
        StringBuilder stringBuilder = new StringBuilder();

        Pattern nameOrEmailPattern = Pattern.compile("(?<=;)[a-zA-Zа-яА-Я]+\\s[a-zA-Zа-яА-Я]+(?=;)|(?<=;)\\S+($|(?=\\n))");
        Matcher matcher = nameOrEmailPattern.matcher(input);

        // Find name or email by turn
        for (int i = 0; matcher.find(); i++) {
            stringBuilder.append(matcher.group());
            if (i % 2 == 0) {
                stringBuilder.append(" (email: ");
            } else {
                stringBuilder.append(")\n");
            }
        }

        // Delete last '\n' character in the text
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    public static String convert3(String input) {
        StringBuilder stringBuilder = new StringBuilder();

        String usedDomains = "";

        Pattern domainPattern = Pattern.compile("(?<=@)\\w+\\.\\w+($|(?=\\n))");
        Matcher domainMatcher = domainPattern.matcher(input);

        // Find domains
        while (domainMatcher.find()) {
            String domain = domainMatcher.group();

            if (usedDomains.contains(domain)) {
                continue;
            }

            usedDomains += domain + ";";

            Pattern loginForDomainPattern = Pattern.compile("(^|(?<=\\n))[a-zA-Zа-яА-Я]+(?=;.+;.+" + domain + ")");
            Matcher loginMatcher = loginForDomainPattern.matcher(input);
            stringBuilder.append(domain).append(" ==> ");

            // Find logins for specified domain
            for (int i = 0; loginMatcher.find(); i++) {
                stringBuilder.append(loginMatcher.group());
                stringBuilder.append(", ");
            }
            stringBuilder.append("\n");

            // Delete last ', ' characters in the line
            stringBuilder.delete(stringBuilder.length() - 3, stringBuilder.length() - 1);
        }

        // Delete last '\n' character in the text
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }
}
