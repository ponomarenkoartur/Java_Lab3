package ua.nure.ponomarenko.task3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part5 {
    private static Pattern loginPattern = Pattern.compile("(^|(?<=\\n))[a-zA-Zа-яА-Я]+(?=;)");
    private static Pattern namePattern = Pattern.compile("(?<=;)[a-zA-Z]+\\s[a-zA-Z]+(?=;)");
    private static Pattern emailPattern = Pattern.compile("(?<=;)\\S+$");
    private static Pattern domainPattern = Pattern.compile("(?<=@)[a-zA-Z]+\\.[a-zA-Z]+$");
    private static Pattern nameAndEmailPattern = Pattern.compile("(?<=;)[a-zA-Zа-яА-Я]+\\s[a-zA-Zа-яА-Я]+(?=;)|(?<=;)\\S+($|(?=\\n))");
    private static Pattern loginAndEmailPattern = Pattern.compile("(^|(?<=\\n))[a-zA-Zа-яА-Я]+(?=;)|(?<=;)\\S+($|(?=\\n))");


    public static void main(String[] args) {
        String input = "ivanov;Ivan Ivanov;ivanov@mail.ru\n" +
                "петров;Петр Петров;petrov@google.com\n" +
                "lennon;John Lennon;lennon@google.com";
        System.out.println(convert2(input));
    }

    public static String convert1(String input) {
        StringBuilder stringBuilder = new StringBuilder();

        Matcher matcher = loginAndEmailPattern .matcher(input);
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

        Matcher matcher = nameAndEmailPattern.matcher(input);
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

        // Find domain
        Matcher matcher = domainPattern.matcher(input);
        if (matcher.find()) {
            // Append domain
            stringBuilder.append(matcher.group());
            stringBuilder.append(" ==> ");
        }

        // Find login
        matcher = loginPattern.matcher(input);
        if (matcher.find()) {
            // Append login
            stringBuilder.append(matcher.group());
            stringBuilder.append(")");
        }

        return stringBuilder.toString();
    }

}
