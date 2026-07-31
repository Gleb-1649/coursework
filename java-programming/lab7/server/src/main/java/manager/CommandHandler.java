package manager;

import commands.*;
import common.network.CommandRequest;
import common.network.CommandResponse;
import common.model.Person;
import storage.StorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CommandHandler {
    private static final Logger log = LoggerFactory.getLogger(CommandHandler.class);

    private final UserManager userMgr;
    private final CollectionManager collMgr;
    private final Map<String, Command> commands = new HashMap<>();

    public CommandHandler(UserManager um, CollectionManager cm) {
        this.userMgr = um;
        this.collMgr = cm;

        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand(cm));
        commands.put("show", new ShowCommand(cm));
        commands.put("add", new AddCommand(cm));
        commands.put("update", new UpdateCommand(cm));
        commands.put("remove_by_id", new RemoveByIdCommand(cm));
        commands.put("clear", new ClearCommand(cm));
        commands.put("shuffle", new ShuffleCommand(cm));
        commands.put("sort", new SortCommand(cm));
        commands.put("remove_lower", new RemoveLowerCommand(cm));
        commands.put("count_less_than_location", new CountLessThanLocationCommand(cm));
        commands.put("print_ascending", new PrintAscendingCommand(cm));
        commands.put("print_unique_eye_color", new PrintUniqueEyeColorCommand(cm));
        commands.put("execute_script", new ExecuteScriptCommand(this));
        commands.put("exit", new ExitCommand());
    }

    public CommandResponse handle(CommandRequest req) {
        String cmd  = req.getCommandName();
        String user = req.getLogin();
        log.info("Получена команда '{}' от '{}'", cmd, user);

        long start = System.nanoTime();
        String result;

        try {
            if ("register".equals(cmd)) {
                try {
                    userMgr.register(user, req.getPasswordHash());
                    result = "Registered";
                } catch (StorageException e) {
                    String msg = e.getMessage();
                    if (msg != null && msg.contains("duplicate key")) {
                        result = "Error: пользователь '" + user + "' уже существует";
                    } else {
                        result = "Storage error: " + (msg == null || msg.isBlank() ? "database unavailable" : msg);
                    }
                }
                return finish(cmd, user, start, result);
            }

            if (userMgr.authenticate(user, req.getPasswordHash()).isEmpty()) {
                result = "Unauthorized";
                return finish(cmd, user, start, result);
            }

            Command c = commands.get(cmd);
            if (c == null) {
                result = "Unknown command: " + cmd;
                return finish(cmd, user, start, result);
            }

            switch (cmd) {
                case "add" -> {
                    Person p = (Person) req.getPayload();
                    result = ((AddCommand) c).execute(p, user);
                }
                case "update" -> {
                    Person p = (Person) req.getPayload();
                    result = ((UpdateCommand) c).execute(p, user);
                }
                case "remove_by_id" -> {
                    String id = (String) req.getPayload();
                    result = ((RemoveByIdCommand) c).execute(user + " " + id);
                }
                case "execute_script" -> {
                    String script = (String) req.getPayload();
                    result = ((ExecuteScriptCommand) c).execute(script, user, req.getPasswordHash());
                }
                default -> {
                    String arg = req.getPayload() instanceof String ? (String) req.getPayload() : "";
                    result = c.execute(arg);
                }
            }

            return finish(cmd, user, start, result);

        } catch (StorageException e) {
            String msg = e.getMessage();
            if (msg == null || msg.isBlank()) msg = "database unavailable";
            result = "Storage error: " + msg;
            return finish(cmd, user, start, result);

        } catch (Exception e) {
            result = "Error: " + e.getMessage();
            return finish(cmd, user, start, result);
        }
    }

    private CommandResponse finish(String cmd,
                                   String user,
                                   long startNano,
                                   String result) {
        long durationMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNano);

        if ("show".equals(cmd)) {
            log.info("Команда '{}' пользователя '{}' выполнена за {} ms", cmd, user, durationMs);
        } else {
            String single = result.replaceAll("\\r?\\n", " | ");
            log.info("Команда '{}' пользователя '{}' выполнена за {} ms; результат: {}",
                    cmd, user, durationMs, single);
        }

        return new CommandResponse(result);
    }

    public String handleCommand(String reqLine) {
        String[] parts = reqLine.trim().split("\\s+", 2);
        String name = parts[0];
        String arg  = parts.length > 1 ? parts[1] : "";
        Command c = commands.get(name);
        if (c == null) {
            return "Unknown command: " + name;
        }
        return c.execute(arg);
    }
}
