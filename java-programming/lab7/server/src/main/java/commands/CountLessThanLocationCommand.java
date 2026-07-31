package commands;

import manager.CollectionManager;

public class CountLessThanLocationCommand implements Command {
    private static final long serialVersionUID = 1L;
    private final CollectionManager cm;

    public CountLessThanLocationCommand(CollectionManager cm) { this.cm = cm; }

    @Override public String execute(String args) {
        long c = cm.countLessThanLocation(args.trim());
        return "Count=" + c;
    }
}
