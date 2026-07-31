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

    // Новый поле — владелец
    private String owner;

    public Person(String id,
                  String name,
                  Coordinates coordinates,
                  ZonedDateTime creationDate,
                  Float height,
                  Float weight,
                  Color eyeColor,
                  Country nationality,
                  Location location)
    {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id not empty");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name not empty");
        if (coordinates == null) throw new IllegalArgumentException("coordinates not null");
        if (creationDate == null) throw new IllegalArgumentException("creationDate not null");
        if (height == null || height <= 0) throw new IllegalArgumentException("height>0");
        if (weight != null && weight <= 0) throw new IllegalArgumentException("weight>0 if set");
        if (nationality == null) throw new IllegalArgumentException("nationality not null");
        if (location == null) throw new IllegalArgumentException("location not null");

        this.id            = id;
        this.name          = name;
        this.coordinates   = coordinates;
        this.creationDate  = creationDate;
        this.height        = height;
        this.weight        = weight;
        this.eyeColor      = eyeColor;
        this.nationality   = nationality;
        this.location      = location;
        this.owner         = null;  // будет устанавливаться при добавлении/загрузке
    }

    public String getId() { return id; }
    public void setId(String id) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id not empty");
        this.id = id;
    }

    public String getName() { return name; }
    public Coordinates getCoordinates() { return coordinates; }
    public ZonedDateTime getCreationDate() { return creationDate; }
    public Float getHeight() { return height; }
    public Float getWeight() { return weight; }
    public Color getEyeColor() { return eyeColor; }
    public Country getNationality() { return nationality; }
    public Location getLocation() { return location; }


    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public int compareTo(Person o) {
        return name.compareTo(o.name);
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
                ", owner='" + owner + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person p = (Person) o;
        return Objects.equals(id, p.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
