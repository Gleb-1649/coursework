package app.managers;

import app.commands.Command;
import core.dao.LabWorkDao;
import core.dao.UserDao;
import core.protocol.CommandRequest;
import core.protocol.CommandResponse;
import core.protocol.CommandType;
import core.utils.LogUtil;

import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Logger;

public class CommandManager {
    private static final Logger log = LogUtil.getLogger(CommandManager.class.getName());

    private final Map<String, Command> commands = new LinkedHashMap<>();
    private final Deque<String> history = new LinkedList<>();
    private final UserDao userDao;
    private final LabWorkDao lwDao;
    private String currentLogin;

    public CommandManager(UserDao userDao, LabWorkDao lwDao) {
        this.userDao = userDao;
        this.lwDao   = lwDao;

        // регистрация / вход
        register(CommandType.REGISTER.name(), new app.commands.RegisterCommand(userDao));
        register(CommandType.LOGIN.name(),    new app.commands.LoginCommand(userDao));

        // команды работы с коллекцией в БД
        register(CommandType.HELP.name(),                    new app.commands.HelpCommand(this));
        register(CommandType.INFO.name(), new app.commands.InfoCommand(lwDao));
        register(CommandType.SHOW.name(), new app.commands.ShowCommand(lwDao));
        register(CommandType.ADD.name(),  new app.commands.AddCommand(lwDao));
        register(CommandType.UPDATE.name(), new app.commands.UpdateCommand(lwDao));
        register(CommandType.REMOVE_BY_ID.name(),
                new app.commands.RemoveByIdCommand(lwDao));
        register(CommandType.CLEAR.name(),
                new app.commands.ClearCommand(lwDao));
        register(CommandType.HEAD.name(), new app.commands.HeadCommand(lwDao));
        register(CommandType.ADD_IF_MAX.name(),
                new app.commands.AddIfMaxCommand(lwDao));
        register(CommandType.COUNT_LESS_THAN_DIFFICULTY.name(),
                new app.commands.CountLessThanDifficultyCommand(lwDao));
        register(CommandType.MIN_BY_ID.name(),
                new app.commands.MinByIdCommand(lwDao));
        register(CommandType.AVERAGE_OF_MINIMAL_POINT.name(),
                new app.commands.AverageOfMinimalPointCommand(lwDao));
        // exit & script
        register(CommandType.EXECUTE_SCRIPT.name(),
                new app.commands.ExecuteScriptCommand(this));
        register(CommandType.EXIT.name(), new app.commands.ExitCommand());
        register(CommandType.SAVE_SERVER.name(), new app.commands.SaveCommand(lwDao));
    }

    private void register(String name, Command cmd) {
        commands.put(name.toLowerCase(), cmd);
    }

    public void setCurrentLogin(String login) {
        this.currentLogin = login;
    }

    public CommandResponse dispatch(CommandRequest request) {
        String name = request.getCommandName().toLowerCase();
        history.addLast(name);
        if (history.size() > 10) history.removeFirst();

        Command cmd = commands.get(name);
        if (cmd == null) {
            return new CommandResponse(false,
                    "Неизвестная команда: " + name,
                    null);
        }
        try {
            return cmd.executeCommand(request.getPayload());
        } catch (Exception e) {
            log.severe("Ошибка при выполнении " + name + ": " + e);
            return new CommandResponse(false,
                    "Ошибка: " + e.getMessage(),
                    null);
        }
    }

    /** Используется HelpCommand */
    public Map<String, Command> getCommands() {
        return commands;
    }

    /** Используется ExecuteScriptCommand и HistoryCommand */
    public Deque<String> getHistory() {
        return history;
    }
}
