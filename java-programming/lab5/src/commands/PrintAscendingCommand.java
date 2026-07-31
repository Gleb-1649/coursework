package commands;

import manager.CollectionManager;

public class PrintAscendingCommand implements Command {
    private final CollectionManager cm;
    public PrintAscendingCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public String execute(String args) {
        StringBuilder sb = new StringBuilder();
        for (module.Person p : cm.getCollection()) {
            sb.append(p).append("\n");
        }
        return sb.toString();
    }
}

