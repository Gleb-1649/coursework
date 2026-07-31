package app.commands;

import core.dao.LabWorkDao;
import core.protocol.CommandResponse;

public class ClearCommand implements Command {
    private final LabWorkDao labWorkDao;

    public ClearCommand(LabWorkDao labWorkDao) {
        this.labWorkDao = labWorkDao;
    }

    @Override
    public CommandResponse executeCommand(Object argument) {
        labWorkDao.fetchAll().forEach(lw -> labWorkDao.delete(lw.getId(), /*owner*/""));
        return new CommandResponse(true, "Коллекция очищена.", null);
    }

    @Override
    public String getDescription() {
        return "clear – очистить коллекцию";
    }
}