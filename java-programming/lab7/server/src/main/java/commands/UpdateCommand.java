package commands;

import common.model.Person;
import manager.CollectionManager;
import storage.StorageException;

public class UpdateCommand implements Command {
    private static final long serialVersionUID = 1L;
    private final CollectionManager cm;

    public UpdateCommand(CollectionManager cm) {
        this.cm = cm;
    }


    @Override
    public String execute(String args) {
        return "Error: use execute(Person,login)";
    }


    public String execute(Person p, String login) {
        String id = p.getId();
        if (id == null || id.isBlank()) return "Error: id needed";

        try {
            boolean ok = cm.update(id, p, login);
            return ok ? "Updated" : "Not found or no rights";
        } catch (StorageException e) {
            return "Error: " + e.getMessage();
        }
    }
}
