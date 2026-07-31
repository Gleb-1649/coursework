package app.commands;

import core.protocol.CommandResponse;

import java.util.Deque;

public class HistoryCommand implements Command {
    private final Deque<String> history;

    public HistoryCommand(Deque<String> history) {
        this.history = history;
    }

    @Override
    public CommandResponse executeCommand(Object argument) {
        if (history.isEmpty()) {
            return new CommandResponse(true, "История команд пуста.", null);
        }
        StringBuilder sb = new StringBuilder("Последние команды:\n");
        history.forEach(cmd -> sb.append(cmd).append("\n"));
        return new CommandResponse(true, sb.toString(), null);
    }

    @Override
    public String getDescription() {
        return "history – показать последние 10 команд";
    }
}