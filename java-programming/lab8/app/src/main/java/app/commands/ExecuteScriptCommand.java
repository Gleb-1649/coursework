package app.commands;

import core.protocol.CommandRequest;
import core.protocol.CommandResponse;
import app.managers.CommandManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Выполняет команды из файла-скрипта. Бьёт рекурсию, игнорирует exit внутри скрипта.
 */
public class ExecuteScriptCommand implements Command {
    private static final Set<String> runningScripts = new HashSet<>();
    private final CommandManager cmdMgr;

    public ExecuteScriptCommand(CommandManager cmdMgr) {
        this.cmdMgr = cmdMgr;
    }

    @Override
    public CommandResponse executeCommand(Object argument) {
        if (!(argument instanceof String)) {
            return new CommandResponse(false,
                    "Для execute_script требуется имя файла (String).",
                    null);
        }
        String fileName = (String) argument;
        File script = new File(fileName);
        if (!script.exists() || !script.isFile()) {
            return new CommandResponse(false,
                    "Файл не найден: " + fileName,
                    null);
        }
        if (!runningScripts.add(fileName)) {
            return new CommandResponse(false,
                    "Обнаружена рекурсия: " + fileName,
                    null);
        }

        StringBuilder out = new StringBuilder();
        try (Scanner sc = new Scanner(script)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+", 2);
                String cmdName = parts[0];
                String cmdArg  = parts.length > 1 ? parts[1] : "";

                // exit внутри скрипта — игнорируем
                if ("exit".equalsIgnoreCase(cmdName)) {
                    out.append("[skipped exit]\n");
                    continue;
                }

                CommandRequest req = new CommandRequest(cmdName, cmdArg, null);
                CommandResponse resp = cmdMgr.dispatch(req);
                out
                        .append(cmdName)
                        .append(" -> ")
                        .append(resp.getMessage())
                        .append("\n");
            }
        } catch (FileNotFoundException e) {
            // не должно случиться, т.к. проверили exists()
            return new CommandResponse(false,
                    "Ошибка чтения скрипта: " + e.getMessage(),
                    null);
        } finally {
            runningScripts.remove(fileName);
        }

        return new CommandResponse(true, out.toString(), null);
    }

    @Override
    public String getDescription() {
        return "execute_script <file> — выполнить команды из файла";
    }
}
