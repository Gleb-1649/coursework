package module;

import enumeration.Color;
import enumeration.Country;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Класс {@code Person} представляет человека (автора) и содержит информацию,
 * необходимую для создания объекта лабораторной работы.
 * <p>
 * Поле {@code id} генерируется автоматически, должно быть уникальным и больше 0.
 * Поле {@code name} не может быть null или пустым.
 * Поле {@code coordinates} не может быть null.
 * Поле {@code creationDate} не может быть null и генерируется автоматически.
 * Поле {@code height} не может быть null и должно быть больше 0.
 * Поле {@code weight} может быть null, но если задано, должно быть больше 0.
 * Поле {@code eyeColor} может быть null.
 * Поле {@code nationality} не может быть null.
 * Поле {@code location} может быть null.
 * </p>
 */
public class Person implements Comparable<Person> {
    private String id;
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private Float height;
    private Float weight;
    private Color eyeColor;
    private Country nationality;
    private Location location;

    /**
     * Создает объект {@code Person}.
     *
     * @param id             уникальный идентификатор (не пустой)
     * @param name           имя (не null, не пустой)
     * @param coordinates  координаты (не null)
     * @param creationDate   дата создания (не null)
     * @param height         рост (не null, > 0)
     * @param weight         вес (может быть null, если задан, > 0)
     * @param eyeColor       цвет глаз (может быть null)
     * @param nationality    национальность (не null)
     * @param location       местоположение (может быть null)
     * @throws IllegalArgumentException если какое-либо поле не соответствует требованиям
     */
    public Person( String id, String name, Coordinates coordinates, ZonedDateTime creationDate,
                  Float height, Float weight, Color eyeColor, Country nationality, Location location) {
        if (id ==null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Ошибка в поле 'id': значение не может быть пустым.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ошибка в поле 'name': значение не может быть null или пустым.");
        }
        if (coordinates == null) {
            throw new IllegalArgumentException("Ошибка в поле 'coordinates': значение не может быть null.");
        }
        if (creationDate == null) {
            throw new IllegalArgumentException("Ошибка в поле 'creationDate': значение не может быть null.");
        }
        if (height == null || height <= 0) {
            throw new IllegalArgumentException("Ошибка в поле 'height': значение не может быть null и должно быть больше 0.");
        }
        if (weight != null && weight <= 0) {
            throw new IllegalArgumentException("Ошибка в поле 'weight': если задано, оно должно быть больше 0.");
        }
        if (nationality == null) {
            throw new IllegalArgumentException("Ошибка в поле 'nationality': значение не может быть null.");
        }
        // location может быть null, проверка производится в конструкторе Location, если он не null
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.height = height;
        this.weight = weight;
        this.eyeColor = eyeColor;
        this.nationality = nationality;
        this.location = location;
    }

    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", height=" + height +
                ", weight=" + weight +
                ", eyeColor=" + eyeColor +
                ", nationality=" + nationality +
                ", location=" + location +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Ошибка в поле 'id': значение не может быть пустым.");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Float getHeight() {
        return height;
    }

    public Float getWeight() {
        return weight;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public Location getLocation() {
        return location;
    }
}


