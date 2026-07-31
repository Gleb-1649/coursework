package common.network;

import java.io.Serializable;

public class CommandRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String commandName;
    private Object payload;  // Если команда требует передачи объекта (например, Person для add)

    public CommandRequest(String commandName, Object payload) {
        this.commandName = commandName;
        this.payload = payload;
    }

    public String getCommandName() {
        return commandName;
    }

    public Object getPayload() {
        return payload;
    }
}


