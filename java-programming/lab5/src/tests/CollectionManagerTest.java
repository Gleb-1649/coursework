package tests;

import org.junit.Test;
import static org.junit.Assert.*;
import manager.CollectionManager;
import module.Person;
import module.Coordinates;
import module.Location;
import java.time.ZonedDateTime;
import enumeration.Color;
import enumeration.Country;
import utils.XmlUtils;

public class CollectionManagerTest {

    @Test
    public void testAddAndClearCollection() {
        XmlUtils xmlUtils = new XmlUtils();
        CollectionManager manager = new CollectionManager("nonexistent.xml", xmlUtils);
        assertEquals(0, manager.getCollection().size());
        Coordinates coords = new Coordinates(200L, 100);
        Location loc = new Location(15.0, 25, 2.0f, "ТестЛокация");
        Person person = new Person("temp-id", "Иван", coords, ZonedDateTime.now(), 180f, 80f, Color.WHITE, Country.INDIA, loc);
        manager.add(person);
        assertEquals(1, manager.getCollection().size());
        manager.clear();
        assertEquals(0, manager.getCollection().size());
    }

    @Test
    public void testSortCollection() {
        XmlUtils xmlUtils = new XmlUtils();
        CollectionManager manager = new CollectionManager("nonexistent.xml", xmlUtils);
        Coordinates coords = new Coordinates(150L, 50);
        Location loc = new Location(10.0, 20, 1.5f, "ТестЛокация");
        ZonedDateTime now = ZonedDateTime.now();
        Person p1 = new Person("id1", "Дмитрий", coords, now, 170f, 70f, Color.BROWN, Country.INDIA, loc);
        Person p2 = new Person("id2", "Алексей", coords, now, 165f, 60f, Color.BLACK, Country.ITALY, loc);
        Person p3 = new Person("id3", "Виктор", coords, now, 180f, 80f, Color.WHITE, Country.JAPAN, loc);
        manager.add(p1);
        manager.add(p2);
        manager.add(p3);
        manager.sort();
        // Ожидаемый порядок: "Алексей", "Виктор", "Дмитрий" (лексикографически)
        assertEquals("Алексей", manager.getCollection().get(0).getName());
        assertEquals("Виктор", manager.getCollection().get(1).getName());
        assertEquals("Дмитрий", manager.getCollection().get(2).getName());
    }
}

