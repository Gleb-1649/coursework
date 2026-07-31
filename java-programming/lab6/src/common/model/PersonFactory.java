package common.model;

import common.enumeration.Color;
import common.enumeration.Country;
import java.time.ZonedDateTime;

public class PersonFactory {
    private final IdGenerator idGenerator = new IdGenerator();

    public PersonFactory() { }

    public Person createPerson(String name,
                               Coordinates coordinates,
                               Float height,
                               Float weight,
                               Color eyeColor,
                               Country nationality,
                               Location location) {
        String generatedId = idGenerator.generateId();
        ZonedDateTime creationDate = ZonedDateTime.now();
        return new Person(generatedId, name, coordinates, creationDate, height, weight, eyeColor, nationality, location);
    }
}
