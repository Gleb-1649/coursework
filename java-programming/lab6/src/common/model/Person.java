package common.model;

import common.enumeration.Color;
import common.enumeration.Country;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Person implements Comparable<Person>, Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private Float height;
    private Float weight;
    private Color eyeColor;
    private Country nationality;
    private Location location;

    public Person(String id, String name, Coordinates coordinates, ZonedDateTime creationDate,
                  Float height, Float weight, Color eyeColor, Country nationality, Location location) {
        if (id == null || id.trim().isEmpty()) {
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

    // Геттеры и сеттеры
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
