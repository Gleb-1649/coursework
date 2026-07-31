package common.model;

import common.enumeration.Color;
import common.enumeration.Country;

import java.time.ZonedDateTime;

public class PersonFactory {
    public PersonFactory() {}

    public Person createPerson(
            String name,
            Coordinates coords,
            Float height,
            Float weight,
            Color eyeColor,
            Country nationality,
            Location location
    ) {

        return new Person("0", name, coords, ZonedDateTime.now(),
                height, weight, eyeColor, nationality, location);
    }
}
