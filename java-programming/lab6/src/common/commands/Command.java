package common.commands;

import java.io.Serializable;

public interface Command extends Serializable {
    String execute(String args);
}

