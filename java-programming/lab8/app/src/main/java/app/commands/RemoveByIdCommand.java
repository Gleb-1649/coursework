package app.commands;

import core.dao.LabWorkDao;
import core.protocol.CommandResponse;

public class RemoveByIdCommand implements Command {
    private final LabWorkDao labWorkDao;

    public RemoveByIdCommand(LabWorkDao labWorkDao) {
        this.labWorkDao = labWorkDao;
    }

    @Override
    public CommandResponse executeCommand(Object argument) {
        if (!(argument instanceof String)) {
            return new CommandResponse(false,
                    "Для remove_by_id ожидается id в виде строки.",
                    null);
        }
        try {
            long id = Long.parseLong((String) argument);
            boolean ok = labWorkDao.delete(id, /*ownerLogin*/"");
            return new CommandResponse(ok,
                    ok ? "Элемент удалён." : "Элемент не найден.",
                    null);
        } catch (NumberFormatException e) {
            return new CommandResponse(false,
                    "Ошибка: id должен быть числом.",
                    null);
        }
    }

    @Override
    public String getDescription() {
        return "remove_by_id – удалить элемент по id";
    }
}
