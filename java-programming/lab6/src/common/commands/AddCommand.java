package common.commands;

import common.model.Person;
import manager.CollectionManager;
import common.model.PersonFactory;
import common.utils.UserInputUtils;

public class AddCommand implements Command {
    private static final long serialVersionUID = 1L;

    private final CollectionManager collectionManager;
    private final PersonFactory personFactory;

    public AddCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        this.personFactory = new PersonFactory();
    }

    @Override
    public String execute(String args) {
        UserInputUtils inputUtils = new UserInputUtils(collectionManager);
        Person rawPerson = inputUtils.readPerson();
        Person finalPerson = personFactory.createPerson(
                rawPerson.getName(),
                rawPerson.getCoordinates(),
                rawPerson.getHeight(),
                rawPerson.getWeight(),
                rawPerson.getEyeColor(),
                rawPerson.getNationality(),
                rawPerson.getLocation()
        );
        collectionManager.add(finalPerson);
        return "Элемент добавлен (id = " + finalPerson.getId() + ")";
    }
}
