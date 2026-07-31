package commands;

import manager.CollectionManager;

public class RemoveByIDCommand implements Command {
    private final CollectionManager cm;
    public RemoveByIDCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public String execute(String args) {
        String id = args.trim();
        if (id.isEmpty()) {
            return "Ошибка: нужно указать id для удаления.";
        }
        cm.removeById(id);
        return "Элемент удалён.";
    }
}

