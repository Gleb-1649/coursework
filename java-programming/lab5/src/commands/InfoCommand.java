package commands;

import manager.CollectionManager;

public class InfoCommand implements Command {
    private final CollectionManager cm;
    public InfoCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public String execute(String args) {
        return cm.getInfo();
    }
}
