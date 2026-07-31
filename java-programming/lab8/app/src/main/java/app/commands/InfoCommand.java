package app.commands;

import core.dao.LabWorkDao;
import core.protocol.CommandResponse;

public class InfoCommand implements Command {
    private final LabWorkDao labWorkDao;

    public InfoCommand(LabWorkDao labWorkDao) {
        this.labWorkDao = labWorkDao;
    }

    @Override
    public CommandResponse executeCommand(Object argument) {
        long count = labWorkDao.fetchAll().size();
        String info = "Количество элементов: " + count;
        return new CommandResponse(true, info, null);
    }

    @Override
    public String getDescription() {
        return "info – вывести информацию о коллекции";
    }
}
