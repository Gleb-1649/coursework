package core.objects;

import core.enums.Color;
import core.enums.Country;

import java.io.Serializable;

/**
 * Класс Person – автор LabWork.
 * Тоже обязательно implements Serializable.
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int weight;        // > 0
    private Color eyeColor;    // может быть null
    private Color hairColor;   // не может быть null
    private Country nationality; // не null

    public Person(String name, int weight, Color eyeColor, Color hairColor, Country nationality) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("name не может быть пустым");
        if (weight <= 0)
            throw new IllegalArgumentException("weight должен быть > 0");
        if (hairColor == null)
            throw new IllegalArgumentException("hairColor не может быть null");
        if (nationality == null)
            throw new IllegalArgumentException("nationality не может быть null");

        this.name = name;
        this.weight = weight;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }
    public int getWeight() {
        return weight;
    }
    public Color getEyeColor() {
        return eyeColor;
    }
    public Color getHairColor() {
        return hairColor;
    }
    public Country getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", eyeColor=" + eyeColor +
                ", hairColor=" + hairColor +
                ", nationality=" + nationality +
                '}';
    }
}
