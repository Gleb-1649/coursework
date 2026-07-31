package commands;

import manager.CollectionManager;

public class PrintAscendingCommand implements Command {
    private static final long serialVersionUID = 1L;
    private final CollectionManager cm;

    public PrintAscendingCommand(CollectionManager cm) { this.cm = cm; }

    @Override public String execute(String args) {
        StringBuilder sb = new StringBuilder();
        cm.getCollection().stream().sorted()
                .forEach(p -> sb.append(p).append("\n"));
        return sb.toString();
    }
}
