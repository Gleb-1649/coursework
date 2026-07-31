package commands;

import manager.CollectionManager;

public class SortCommand implements Command {
    private static final long serialVersionUID = 1L;
    private final CollectionManager cm;

    public SortCommand(CollectionManager cm) { this.cm = cm; }

    @Override public String execute(String login) {
        cm.sort(login);
        return "Sorted";
    }
}