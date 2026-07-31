import manager.CollectionManager;
import manager.CommandHandler;
import utils.XmlUtils;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileName;
        if (args.length < 1) {
            fileName = "collection/lab5.xml";
            System.out.println("Не передан аргумент командной строки. Используем файл: " + fileName);
        } else {
            fileName = args[0];
        }

        XmlUtils xmlUtils = new XmlUtils();
        CollectionManager collectionManager = new CollectionManager(fileName, xmlUtils);
        CommandHandler commandHandler = new CommandHandler(collectionManager);

        System.out.println("Коллекция загружена. Введите команду (help для справки):");
        Scanner scanner = new Scanner(System.in);
        String result = "";
        while (!"Выход из программы.".equalsIgnoreCase(result)) {
            System.out.print("> ");
            String input = scanner.nextLine();
            result = commandHandler.handleCommand(input);
            System.out.println(result);
        }
    }
}
