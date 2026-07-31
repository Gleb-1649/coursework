package core.protocol;

import java.io.Serializable;

public class CommandRequest implements Serializable {
    private final String commandName;
    private final Object payload;    // raw
    private String login;            // сессия

    public CommandRequest(String commandName, Object payload, String login) {
        this.commandName = commandName;
        this.payload     = payload;
        this.login       = login;
    }
    public String getCommandName() { return commandName; }
    public Object getPayload()     { return payload;     }
    public String getLogin()       { return login;       }
    /** Позволяет Session прокинуть login после авторизации */
    public void setLogin(String login) { this.login = login; }
}
