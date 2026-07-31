package file;

import java.io.*;

/**
 * Класс FileHandler обеспечивает чтение и запись файлов.
 */
public class FileHandler {
    public String readFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Файл не найден.");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, bytesRead, "UTF-8"));
            }
        } catch (SecurityException se) {
            System.out.println("Ошибка доступа: недостаточно прав для чтения файла " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return sb.toString();
    }

    public void writeToFile( String content, String fileName) {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8")) {
            writer.write(content);
        } catch (SecurityException se) {
            System.out.println("Ошибка доступа: недостаточно прав для записи в файл " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при записи файла: " + e.getMessage());
        }
    }
}

