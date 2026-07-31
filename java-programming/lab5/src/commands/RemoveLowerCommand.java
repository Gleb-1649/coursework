package commands;

import manager.CollectionManager;
import utils.UserInputUtils;
import module.Person;

public class RemoveLowerCommand implements Command {
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

