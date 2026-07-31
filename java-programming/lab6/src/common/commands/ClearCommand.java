package common.commands;

import manager.CollectionManager;

public class ClearCommand implements Command {
    private static final long serialVersionUID = 1L;
    private final CollectionManager cm;

    public ClearCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public String execute(String args) {
        cm.clear();
        return "Коллекция очищена.";
    }
}
