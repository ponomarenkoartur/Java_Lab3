package ua.nure.ponomarenko.task3;

import java.io.*;

public class FileReader {
    public static String readTextFromFile(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();

        File file = new File(filePath);
        FileInputStream fileInputStream;
        BufferedInputStream bufferedInputStream;
        DataInputStream dataInputStream;

        try {
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            dataInputStream = new DataInputStream(bufferedInputStream);

            while (dataInputStream.available() != 0) {
                stringBuilder.append(dataInputStream.readLine());
                stringBuilder.append("\n");
            }

            // Delete last '\n' character
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);

            fileInputStream.close();
            bufferedInputStream.close();
            dataInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
