package commands;

import manager.CollectionManager;
import utils.UserInputUtils;

public class UpdateCommand implements Command {
    private final CollectionManager cm;
    public UpdateCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public String execute(String args) {
        if (args.isEmpty()) {
            return "Ошибка: нужно указать id для обновления.";
        }
        String id = args.trim();
        UserInputUtils inputUtils = new UserInputUtils(cm);
        inputUtils.setSilent(true);
        module.Person person = inputUtils.readPerson();
        cm.update(id, person);
        return "Элемент с id " + id + " обновлён.";
    }
}

