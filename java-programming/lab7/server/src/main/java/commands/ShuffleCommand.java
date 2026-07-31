package commands;

import manager.CollectionManager;

public class ShuffleCommand implements Command {
    private static final long serialVersionUID = 1L;
    private final CollectionManager cm;

    public ShuffleCommand(CollectionManager cm) { this.cm = cm; }

    @Override public String execute(String login) {
        cm.shuffle(login);
        return "Shuffled";
    }
}