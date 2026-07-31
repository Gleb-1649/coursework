package common.commands;

import manager.CollectionManager;

public class RemoveByIdCommand implements Command {
    private static final long serialVersionUID = 1L;
    private final CollectionManager cm;
    public RemoveByIdCommand(CollectionManager cm) {
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
