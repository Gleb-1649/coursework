package tests;

import org.junit.Test;
import static org.junit.Assert.*;
import module.Person;
import module.Coordinates;
import module.Location;
import java.time.ZonedDateTime;
import enumeration.Color;
import enumeration.Country;

public class PersonTest {

    @Test
    public void testPersonCreationValid() {
        Coordinates coords = new Coordinates(100L, 50);
        Location loc = new Location(10.0, 20, 1.5f, "ТестЛокация");
        ZonedDateTime now = ZonedDateTime.now();
        Person person = new Person("id-001", "Иван Иванов", coords, now, 170f, 65f, Color.BLACK, Country.ITALY, loc);
        assertEquals("Иван Иванов", person.getName());
        assertEquals(coords, person.getCoordinates());
        assertEquals(loc, person.getLocation());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPersonCreationInvalidName() {
        Coordinates coords = new Coordinates(100L, 50);
        Location loc = new Location(10.0, 20, 1.5f, "ТестЛокация");
        new Person("id-002", "", coords, ZonedDateTime.now(), 170f, 65f, Color.BLACK, Country.ITALY, loc);
    }
}

