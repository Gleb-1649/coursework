package common.commands;

import common.file.FileHandler;
import manager.CommandHandler;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ExecuteScriptCommand implements Command {
    private static final long serialVersionUID = 1L;
    private final FileHandler fh;
    private final CommandHandler commandHandler;
    private static final Set<String> executingScripts = new HashSet<>();

    public ExecuteScriptCommand(FileHandler fh, CommandHandler commandHandler) {
        this.fh = fh;
        this.commandHandler = commandHandler;
    }

    @Override
    public String execute(String args) {
        String fileName = args.trim();
        if (executingScripts.contains(fileName)) {
            return "Ошибка: рекурсивное выполнение скрипта " + fileName;
        }
        executingScripts.add(fileName);
        String script = fh.readFromFile(fileName);
        if (script.isEmpty()) {
            executingScripts.remove(fileName);
            return "Скрипт пуст или не найден.";
        }
        StringBuilder result = new StringBuilder();
        Scanner scriptScanner = new Scanner(new ByteArrayInputStream(script.getBytes(StandardCharsets.UTF_8)));
        while (scriptScanner.hasNextLine()) {
            String line = scriptScanner.nextLine().trim();
            if (line.isEmpty()) continue;
            result.append("Выполнение команды: ").append(line).append("\n");
            if (line.equalsIgnoreCase("add") ||
                    line.toLowerCase().startsWith("update") ||
                    line.equalsIgnoreCase("remove_lower")) {
                int requiredLines = 11;
                StringBuilder additionalInputBuilder = new StringBuilder();
                for (int j = 0; j < requiredLines && scriptScanner.hasNextLine(); j++) {
                    additionalInputBuilder.append(scriptScanner.nextLine()).append("\n");
                }
                InputStream originalIn = System.in;
                try {
                    System.setIn(new ByteArrayInputStream(additionalInputBuilder.toString().getBytes(StandardCharsets.UTF_8)));
                    String commandResult = commandHandler.handleCommand(line);
                    result.append(commandResult).append("\n");
                } finally {
                    System.setIn(originalIn);
                }
            } else {
                String commandResult = commandHandler.handleCommand(line);
                result.append(commandResult).append("\n");
            }
        }
        scriptScanner.close();
        executingScripts.remove(fileName);
        return result.toString();
    }
}
