package app.commands;

import core.dao.LabWorkDao;
import core.objects.LabWork;
import core.protocol.CommandResponse;

import java.util.List;

public class ShowCommand implements Command {
    private final LabWorkDao labWorkDao;

    public ShowCommand(LabWorkDao labWorkDao) {
        this.labWorkDao = labWorkDao;
    }

    @Override
    public CommandResponse executeCommand(Object argument) {
        List<LabWork> list = labWorkDao.fetchAll();
        if (list.isEmpty()) {
            return new CommandResponse(false, "Коллекция пуста.", null);
        }
        StringBuilder sb = new StringBuilder();
        for (LabWork lw : list) {
            sb.append(lw).append("\n");
        }
        return new CommandResponse(true, sb.toString(), list);
    }

    @Override
    public String getDescription() {
        return "show – вывести все элементы коллекции";
    }
}
