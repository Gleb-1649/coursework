package commands;

import common.model.Person;
import manager.CollectionManager;
import storage.StorageException;

public class AddCommand implements Command {
    private static final long serialVersionUID = 1L;
    private final CollectionManager cm;

    public AddCommand(CollectionManager cm) {
        this.cm = cm;
    }

    @Override
    public String execute(String args) {
        return "Error: AddCommand must be called with a Person payload";
    }

    public String execute(Person p, String login) {
        try {
            cm.add(p, login);
            return "Added id=" + p.getId();
        } catch (StorageException e) {
            return "Error: " + e.getMessage();
        }
    }
}