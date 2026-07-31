package commands;

import manager.CollectionManager;

public class SortCommand implements Command {
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
