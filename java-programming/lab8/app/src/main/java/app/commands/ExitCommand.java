package app.commands;

import core.protocol.CommandResponse;

public class ExitCommand implements Command {
    @Override
    public CommandResponse executeCommand(Object argument) {
        return new CommandResponse(true, "exit – завершение сеанса.", null);
    }

    @Override
    public String getDescription() {
        return "exit – завершить работу клиента";
    }
}