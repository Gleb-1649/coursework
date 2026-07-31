package app.commands;

import core.dao.LabWorkDao;
import core.objects.LabWork;
import core.protocol.CommandResponse;

public class AddCommand implements Command {
    private final LabWorkDao labWorkDao;

    public AddCommand(LabWorkDao labWorkDao) {
        this.labWorkDao = labWorkDao;
    }

    @Override
    public CommandResponse executeCommand(Object argument) {
        if (!(argument instanceof LabWork)) {
            return new CommandResponse(false,
                    "Для add ожидается объект LabWork (клиент сам должен сформировать его).",
                    null);
        }
        LabWork lw = (LabWork) argument;
        try {
            return labWorkDao.insert(lw)
                    .map(id -> new CommandResponse(true,
                            "Элемент добавлен с id=" + id,
                            null))
                    .orElseGet(() -> new CommandResponse(false,
                            "Не удалось добавить LabWork",
                            null));
        } catch (Exception e) {
            return new CommandResponse(false,
                    "Ошибка при добавлении: " + e.getMessage(),
                    null);
        }
    }

    @Override
    public String getDescription() {
        return "add – добавить новый элемент";
    }
}
