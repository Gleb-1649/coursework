package core.protocol;

import java.io.Serializable;

public class CommandResponse implements Serializable {
    private final boolean success;
    private final String message;
    private final Object data;   // raw

    public CommandResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data    = data;
    }
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public Object getData()    { return data;    }
}
