package commands;

import manager.CollectionManager;

public class ClearCommand implements Command {
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
