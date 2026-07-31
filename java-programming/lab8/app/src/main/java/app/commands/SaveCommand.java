package app.commands;

import core.dao.LabWorkDao;
import core.protocol.CommandResponse;

public class SaveCommand implements Command {
    private final LabWorkDao dao;

    public SaveCommand(LabWorkDao dao) {
        this.dao = dao;
    }

    @Override
    public CommandResponse executeCommand(Object argument) {
        return new CommandResponse(true,
                "Коллекция хранится в базе, сохранение не требуется.",
                null);
    }

    @Override
    public String getDescription() {
        return "save_server : (сервер) сохранить коллекцию";
    }
}
