package common.commands;

import manager.CollectionManager;
import common.utils.UserInputUtils;
import common.model.Person;

public class UpdateCommand implements Command {
    private static final long serialVersionUID = 1L;
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
        Person person = inputUtils.readPerson();
        cm.update(id, person);
        return "Элемент с id " + id + " обновлён.";
    }
}

