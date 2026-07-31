package commands;

import manager.CollectionManager;

public class CountLessThanLocationCommand implements Command {
    private final CollectionManager cm;
    public CountLessThanLocationCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public String execute(String args) {
        cm.countLessThanLocation(args.trim());
        return "";
    }
}

