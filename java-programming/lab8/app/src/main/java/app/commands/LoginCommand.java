package app.commands;

import core.dao.UserDao;
import core.protocol.CommandResponse;
import app.server.auth.Authenticator;

public class LoginCommand implements Command {
    private final UserDao userDao;

    public LoginCommand(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public CommandResponse executeCommand(Object argument) {
        if (!(argument instanceof String[])) {
            return new CommandResponse(false,
                    "Для login ожидается массив строк [login, password].",
                    null);
        }
        String[] args = (String[]) argument;
        if (args.length < 2) {
            return new CommandResponse(false,
                    "Использование: login <login> <password>",
                    null);
        }
        String login = args[0];
        String password = args[1];
        boolean ok = Authenticator.login(userDao, login, password);
        if (ok) {
            return new CommandResponse(true,
                    "Успешный вход.",
                    null);
        } else {
            return new CommandResponse(false,
                    "Неверный логин или пароль.",
                    null);
        }
    }

    @Override
    public String getDescription() {
        return "login <login> <password> – войти в систему";
    }
}