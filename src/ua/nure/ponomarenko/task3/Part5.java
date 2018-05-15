package ua.nure.ponomarenko.task3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part5 {
    public static void main(String[] args) {
        String input = "ivanov;Ivan Ivanov;ivanov@mail.ru\n" +
                "петров;Петр Петров;petrov@google.com\n" +
                "lennon;John Lennon;lennon@google.com";
        System.out.println(convert2(input));
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
                break;
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
