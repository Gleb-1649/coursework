package tests;

import org.junit.Test;
import static org.junit.Assert.*;
import module.PersonFactory;
import module.Coordinates;
import module.Location;
import enumeration.Color;
import enumeration.Country;

public class PersonFactoryTest {

    @Test
    public void testAutomaticIdGeneration() {
        PersonFactory factory = new PersonFactory();
        Coordinates coords = new Coordinates(100L, 50);
        Location loc = new Location(10.0, 20, 1.5f, "ТестЛокация");
        module.Person person = factory.createPerson("ТестИмя", coords, 170f, 70f, Color.BLACK, Country.ITALY, loc);
        String generatedId = person.getId();
        assertNotNull("ID должен быть не null", generatedId);
        assertFalse("ID не должен быть пустым", generatedId.trim().isEmpty());
        try {
            java.util.UUID.fromString(generatedId);
        } catch (IllegalArgumentException e) {
            fail("ID не является валидным UUID");
        }
    }
}

