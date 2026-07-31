package commands;

import manager.CollectionManager;
import module.Person;

public class ShowCommand implements Command {
    private final CollectionManager cm;
    public ShowCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public String execute(String args) {
        if (cm.getCollection().isEmpty()) {
            return "Коллекция пуста.";
        }
        StringBuilder sb = new StringBuilder();
        for (Person p : cm.getCollection()) {
            sb.append(p).append("\n");
        }
        return sb.toString();
    }
}

