package common.commands;

import manager.CollectionManager;

public class SortCommand implements Command {
    private static final long serialVersionUID = 1L;
    private final CollectionManager cm;
    public SortCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public String execute(String args) {
        cm.sort();
        return "Коллекция отсортирована.";
    }
}

