package commands;

import manager.CollectionManager;
import module.Person;
import module.PersonFactory;
import utils.UserInputUtils;

/**
 * Команда AddCommand добавляет новый элемент в коллекцию.
 */
public class AddCommand implements Command {
    private final CollectionManager collectionManager;
    private final PersonFactory personFactory;

    public AddCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        this.personFactory = new PersonFactory();
    }

    @Override
    public String execute(String args) {
        // Если запускается из скрипта, можно отключить приглашения
        UserInputUtils inputUtils = new UserInputUtils(collectionManager);
        String result = "";
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
        result = "Элемент добавлен (id = " + finalPerson.getId() + ")";
        return result;
    }
}

