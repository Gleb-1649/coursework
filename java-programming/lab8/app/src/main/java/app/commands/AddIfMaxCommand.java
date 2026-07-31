package app.commands;

import core.dao.LabWorkDao;
import core.objects.LabWork;
import core.protocol.CommandResponse;

public class AddIfMaxCommand implements Command {
    private final LabWorkDao labWorkDao;

    public AddIfMaxCommand(LabWorkDao labWorkDao) {
        this.labWorkDao = labWorkDao;
    }

    @Override
    public CommandResponse executeCommand(Object argument) {
        if (!(argument instanceof LabWork)) {
            return new CommandResponse(false,
                    "Для add_if_max ожидается объект LabWork.",
                    null);
        }
        LabWork lw = (LabWork) argument;
        boolean isMax = labWorkDao.fetchAll().stream()
                .allMatch(existing -> lw.compareTo(existing) > 0);
        if (!isMax) {
            return new CommandResponse(false, "Элемент не максимальный.", null);
        }
        return labWorkDao.insert(lw)
                .map(id -> new CommandResponse(true,
                        "Элемент добавлен как максимальный, id=" + id,
                        null))
                .orElseGet(() -> new CommandResponse(false,
                        "Не удалось добавить LabWork",
                        null));
    }

    @Override
    public String getDescription() {
        return "add_if_max – добавить, если больше всех";
    }
}