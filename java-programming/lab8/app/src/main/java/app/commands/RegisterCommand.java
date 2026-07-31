package app.commands;

import core.dao.UserDao;
import core.protocol.CommandResponse;
import app.server.auth.Authenticator;

public class RegisterCommand implements Command {
    private final UserDao userDao;

    public RegisterCommand(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public CommandResponse executeCommand(Object argument) {
        if (!(argument instanceof String[])) {
            return new CommandResponse(false,
                    "Для register ожидается массив строк [login, password].",
                    null);
        }
        String[] args = (String[]) argument;
        if (args.length < 2) {
            return new CommandResponse(false,
                    "Использование: register <login> <password>",
                    null);
        }
        String login = args[0];
        String password = args[1];
        boolean created = Authenticator.register(userDao, login, password);
        if (created) {
            return new CommandResponse(true,
                    "Пользователь успешно зарегистрирован.",
                    null);
        } else {
            return new CommandResponse(false,
                    "Не удалось зарегистрировать (возможно, логин уже занят).",
                    null);
        }
    }

    @Override
    public String getDescription() {
        return "register <login> <password> – зарегистрировать нового пользователя";
    }
}