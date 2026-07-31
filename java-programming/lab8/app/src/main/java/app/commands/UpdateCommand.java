package app.commands;

import core.dao.LabWorkDao;
import core.objects.LabWork;
import core.protocol.CommandResponse;

public class UpdateCommand implements Command {
    private final LabWorkDao dao;

    public UpdateCommand(LabWorkDao dao) {
        this.dao = dao;
    }

    @Override
    public CommandResponse executeCommand(Object argument) {
        if (!(argument instanceof Object[])) {
            return new CommandResponse(false,
                    "Для update нужен массив [id, LabWork].", null);
        }
        Object[] arr = (Object[]) argument;
        if (arr.length != 2 ||
                !(arr[0] instanceof Number) ||
                !(arr[1] instanceof LabWork)) {
            return new CommandResponse(false,
                    "Для update нужен массив [id, LabWork].", null);
        }

        long id = ((Number)arr[0]).longValue();
        LabWork lw = (LabWork) arr[1];
        // LabWork ожидает int id:
        lw.setId((int) id);

        boolean ok = dao.update(lw);
        if (ok) {
            return new CommandResponse(true,
                    "Элемент с id=" + id + " успешно обновлён.", null);
        } else {
            return new CommandResponse(false,
                    "Не удалось обновить: элемент не найден или нет прав.", null);
        }
    }

    @Override
    public String getDescription() {
        return "update – обновить элемент по id (интерактивно)";
    }
}
