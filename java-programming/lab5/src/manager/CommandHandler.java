package manager;

import commands.*;
import file.FileHandler;
import utils.XmlUtils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс CommandHandler сопоставляет команды их строковому представлению и обрабатывает ввод.
 */
public class CommandHandler {
    private final Map<String, Command> commandMap;
    private final Scanner scanner;

    public CommandHandler(CollectionManager collectionManager) {
        this.scanner = new Scanner(System.in);
        this.commandMap = new HashMap<>();
        commandMap.put("help", new HelpCommand());
        commandMap.put("info", new InfoCommand(collectionManager));
        commandMap.put("show", new ShowCommand(collectionManager));
        commandMap.put("add", new AddCommand(collectionManager));
        commandMap.put("update", new UpdateCommand(collectionManager));
        commandMap.put("remove_by_id", new RemoveByIDCommand(collectionManager));
        commandMap.put("clear", new ClearCommand(collectionManager));
        commandMap.put("save", new SaveCommand(collectionManager, new XmlUtils()));
        commandMap.put("execute_script", new ExecuteScriptCommand(new FileHandler(), this));
        commandMap.put("exit", new ExitCommand());
        commandMap.put("shuffle", new ShuffleCommand(collectionManager));
        commandMap.put("remove_lower", new RemoveLowerCommand(collectionManager));
        commandMap.put("sort", new SortCommand(collectionManager));
        commandMap.put("count_less_than_location", new CountLessThanLocationCommand(collectionManager));
        commandMap.put("print_ascending", new PrintAscendingCommand(collectionManager));
        commandMap.put("print_unique_eye_color", new PrintUniqueEyeColorCommand(collectionManager));
    }

    public String handleCommand(String commandLine) {
        String[] parts = commandLine.trim().split("\\s+", 2);
        String command = parts[0].toLowerCase(Locale.getDefault());
        String argument = parts.length > 1 ? parts[1] : "";
        Command cmd = commandMap.get(command);
        return (cmd != null) ? cmd.execute(argument) : "Неизвестная команда.";
    }
}

