package storage;

import common.model.*;
import common.enumeration.*;
import org.bson.Document;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class MongoUtils {
    public static Person documentToPerson(Document d) {
        String id   = d.getObjectId("_id").toHexString();
        String name = d.getString("name");

        Coordinates coords = new Coordinates(
                d.getLong("coord_x"),
                d.getInteger("coord_y")
        );

        Object rawDate = d.get("created_at");
        ZonedDateTime creationDate;
        if (rawDate instanceof Date) {
            creationDate = ZonedDateTime.ofInstant(
                    ((Date) rawDate).toInstant(),
                    ZoneId.systemDefault()
            );
        } else {
            creationDate = ZonedDateTime.parse((String) rawDate);
        }

        Float height = d.getDouble("height").floatValue();

        Float weight = null;
        if (d.containsKey("weight") && d.get("weight") != null) {
            weight = d.getDouble("weight").floatValue();
        }

        String ec = d.getString("eye_color");
        Color eyeColor = ec != null ? Color.valueOf(ec) : null;

        Country nat = Country.valueOf(d.getString("nationality"));

        Location loc = new Location(
                d.getDouble("loc_x"),
                d.getInteger("loc_y"),
                d.getDouble("loc_z").floatValue(),
                d.getString("loc_name")
        );

        Person person = new Person(
                id, name, coords, creationDate,
                height, weight, eyeColor, nat, loc
        );

        // устанавливаем владельца
        person.setOwner(d.getString("owner"));

        return person;
    }
}
