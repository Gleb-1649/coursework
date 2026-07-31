package app.commands;

import core.dao.LabWorkDao;
import core.protocol.CommandResponse;

public class HeadCommand implements Command {
    private final LabWorkDao labWorkDao;

    public HeadCommand(LabWorkDao labWorkDao) {
        this.labWorkDao = labWorkDao;
    }

    @Override
    public CommandResponse executeCommand(Object argument) {
        return labWorkDao.fetchAll().stream()
                .findFirst()
                .<CommandResponse>map(lw ->
                        new CommandResponse(true,
                                "Первый элемент коллекции: " + lw,
                                lw))
                .orElseGet(() ->
                        new CommandResponse(false,
                                "Коллекция пуста.",
                                null));
    }

    @Override
    public String getDescription() {
        return "head – вывести первый элемент коллекции";
    }
}