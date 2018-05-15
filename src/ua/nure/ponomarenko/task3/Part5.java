package ua.nure.ponomarenko.task3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part5 {
    public static void main(String[] args) {
        String input = "ivanov;Ivan Ivanov;ivanov@mail.ru";
        System.out.println(convert1(input));
    }

    public static String convert1(String input) {
        StringBuilder stringBuilder = new StringBuilder();

        Pattern loginPattern = Pattern.compile("^\\w+(?=;)");
        Pattern emailPattern = Pattern.compile("(?<=;)\\S+$");

        Matcher matcher = loginPattern.matcher(input);
        // Find login
        if (matcher.find()) {
            // Append login
            stringBuilder.append(matcher.group());
            stringBuilder.append(" ==> ");
        }

        // Find email
        matcher = emailPattern.matcher(input);
        if (matcher.find()) {
            // Append email
            stringBuilder.append(matcher.group());
        }

        return stringBuilder.toString();
    }

}
