package repository;

import java.io.*;
import java.util.*;


class FileUtil {

    public static <T> void saveToFile(String fileName, Collection<T> items) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (T item : items) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to " + fileName + ": " + e.getMessage());
        }
    }

    public static List<String> readFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) return lines;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = reader.readLine()) != null){
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading from " + fileName + ": " + e.getMessage());
        }
        return lines;
    }
}
