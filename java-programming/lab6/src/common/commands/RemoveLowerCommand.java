package common.commands;

import manager.CollectionManager;
import common.utils.UserInputUtils;
import common.model.Person;

public class RemoveLowerCommand implements Command {
    private static final long serialVersionUID = 1L;
    private final CollectionManager cm;
    public RemoveLowerCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public String execute(String args) {
        UserInputUtils inputUtils = new UserInputUtils(cm);
        inputUtils.setSilent(true);
        Person person = inputUtils.readPerson();
        try {
            cm.removeLower(person);
            return "Удалены элементы, меньшие заданного.";
        } catch(Exception e) {
            return "Ошибка при удалении элементов: " + e.getMessage();
        }
    }
}
