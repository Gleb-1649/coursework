package commands;

import manager.CollectionManager;

public class ShuffleCommand implements Command {
    private final CollectionManager cm;
    public ShuffleCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public String execute(String args) {
        cm.shuffle();
        return "Коллекция перемешана.";
    }
}

