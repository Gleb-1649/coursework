package commands;

import manager.CollectionManager;
import storage.StorageException;

public class RemoveByIdCommand implements Command {
    private static final long serialVersionUID = 1L;
    private final CollectionManager cm;

    public RemoveByIdCommand(CollectionManager cm) {
        this.cm = cm;
    }

    @Override public String execute(String args) {
        String[] parts = args.split(" ",2);
        String login = parts[0];
        String id = parts.length>1?parts[1]:"";
        if (id.isBlank()) return "Error: id needed";
        try {
            boolean ok = cm.removeById(id, login);
            return ok ? "Removed" : "Not found or no rights";
        } catch (StorageException e) {
            return "Error: " + e.getMessage();
        }
    }
}
