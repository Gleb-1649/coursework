package common.commands;

import manager.CollectionManager;

public class PrintUniqueEyeColorCommand implements Command {
    private static final long serialVersionUID = 1L;
    private final CollectionManager cm;
    public PrintUniqueEyeColorCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public String execute(String args) {
        StringBuilder sb = new StringBuilder();
        cm.getCollection().stream()
                .map(p -> p.getEyeColor())
                .distinct()
                .forEach(color -> sb.append(color).append("\n"));
        return sb.toString();
    }
}

