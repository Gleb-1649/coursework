package commands;

import manager.CollectionManager;
import common.model.Person;

public class ShowCommand implements Command {
    private static final long serialVersionUID = 1L;
    private final CollectionManager cm;

    public ShowCommand(CollectionManager cm) {
        this.cm = cm;
    }

    @Override
    public String execute(String args) {
        StringBuilder sb = new StringBuilder();
        for (Person p : cm.getCollection()) {
            sb.append(p).append("\n");
        }
        return sb.length() == 0 ? "Collection empty" : sb.toString();
    }
}