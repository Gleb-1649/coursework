package commands;

import common.model.Person;
import common.utils.UserInputUtils;
import manager.CollectionManager;
import storage.StorageException;

public class RemoveLowerCommand implements Command {
    private static final long serialVersionUID = 1L;
    private final CollectionManager cm;

    public RemoveLowerCommand(CollectionManager cm) {
        this.cm = cm;
    }

    @Override public String execute(String login) {
        UserInputUtils ui = new UserInputUtils();
        ui.setSilent(true);
        Person threshold = ui.readPerson();
        try {
            int cnt = cm.removeLowerPersons(threshold.getName(), login);
            return "Removed " + cnt;
        } catch (StorageException e) {
            return "Error: " + e.getMessage();
        }
    }
}
