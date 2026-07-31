package common.network;

import java.io.Serializable;

public class CommandRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String commandName;
    private final Object payload;
    private final String login;
    private final String passwordHash;

    public CommandRequest(String commandName, Object payload,
                          String login, String passwordHash) {
        this.commandName   = commandName;
        this.payload       = payload;
        this.login         = login;
        this.passwordHash  = passwordHash;
    }

    public String getCommandName() { return commandName; }
    public Object getPayload()     { return payload; }
    public String getLogin()       { return login; }
    public String getPasswordHash(){ return passwordHash; }
}
