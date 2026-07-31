package tests;

import org.junit.Test;
import static org.junit.Assert.*;
import file.FileHandler;
import java.io.File;

public class FileHandlerTest {

    @Test
    public void testWriteAndReadFile() throws Exception {
        FileHandler fileHandler = new FileHandler();
        String content = "Тестовое содержимое файла";
        File tempFile = File.createTempFile("testFile", ".txt");
        tempFile.deleteOnExit();
        fileHandler.writeToFile(content, tempFile.getAbsolutePath());
        String readContent = fileHandler.readFromFile(tempFile.getAbsolutePath());
        assertEquals(content, readContent);
    }
}
