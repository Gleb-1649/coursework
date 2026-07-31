package tests;

import org.junit.Test;
import static org.junit.Assert.*;
import manager.CollectionManager;
import manager.CommandHandler;
import utils.XmlUtils;

public class CommandHandlerTest {

    @Test
    public void testUnknownCommand() {
        XmlUtils xmlUtils = new XmlUtils();
        CollectionManager manager = new CollectionManager("nonexistent.xml", xmlUtils);
        CommandHandler handler = new CommandHandler(manager);
        String result = handler.handleCommand("unknownCommand");
        assertEquals("Неизвестная команда.", result);
    }

    @Test
    public void testHelpCommand() {
        XmlUtils xmlUtils = new XmlUtils();
        CollectionManager manager = new CollectionManager("nonexistent.xml", xmlUtils);
        CommandHandler handler = new CommandHandler(manager);
        String result = handler.handleCommand("help");
        assertTrue(result.contains("Доступные команды:"));
    }
}

