package module;


import enumeration.Color;
import enumeration.Country;
import java.time.ZonedDateTime;

/**
 * Класс PersonFactory создаёт объекты Person, используя IdGenerator для генерации уникального id.
 */
public class PersonFactory {
    private final IdGenerator idGenerator = new IdGenerator();

    public PersonFactory() { }

    /**
     * Создаёт новый объект Person с автоматически сгенерированным UUID.
     *
     * @param name имя (не null, не пустое)
     * @param coordinates координаты (не null)
     * @param height рост (не null, > 0)
     * @param weight вес (может быть null; если задан, > 0)
     * @param eyeColor цвет глаз (может быть null)
     * @param nationality национальность (не null)
     * @param location местоположение (может быть null)
     * @return созданный объект Person
     */
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

