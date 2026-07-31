package app.commands;

import app.managers.CommandManager;
import core.protocol.CommandResponse;

import java.util.Map;

public class HelpCommand implements Command {
    private final CommandManager mgr;

    public HelpCommand(CommandManager mgr) {
        this.mgr = mgr;
    }

    @Override
    public CommandResponse executeCommand(Object argument) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Command> e : mgr.getCommands().entrySet()) {
            sb.append(e.getKey())
                    .append(" : ")
                    .append(e.getValue().getDescription())
                    .append("\n");
        }
        return new CommandResponse(true, sb.toString(), null);
    }

    @Override
    public String getDescription() {
        return "help : вывести справку по командам";
    }
}
