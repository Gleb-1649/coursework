package core.objects;

import core.enums.Difficulty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс LabWork представляет лабораторную работу.
 * Нужно, чтобы он был Serializable для передачи по сети.
 */
public class LabWork implements Comparable<LabWork>, Serializable {
    private static final long serialVersionUID = 1L;

    private static final AtomicInteger nextId = new AtomicInteger(1);

    private int id;
    private String name;
    private Coordinates coordinates;
    private LocalDate creationDate;
    private Long minimalPoint;
    private String description;
    private Difficulty difficulty;
    private Person author;

    /**
     * Конструктор для создания нового объекта LabWork (для клиента).
     * id и creationDate генерируются автоматически на сервере или в этом конструкторе.
     */
    public LabWork(String name,
                   Coordinates coordinates,
                   Long minimalPoint,
                   String description,
                   Difficulty difficulty,
                   Person author) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("name не может быть пустым");
        if (coordinates == null)
            throw new IllegalArgumentException("coordinates не могут быть null");
        if (minimalPoint == null || minimalPoint <= 0)
            throw new IllegalArgumentException("minimalPoint должен быть > 0");
        if (description == null || description.trim().isEmpty())
            throw new IllegalArgumentException("description не может быть пустым");
        if (author == null)
            throw new IllegalArgumentException("author не может быть null");

        // Генерируем id и creationDate (можно генерировать на сервере, зависит от вашей логики)
        this.id = nextId.getAndIncrement();
        this.creationDate = LocalDate.now();

        this.name = name;
        this.coordinates = coordinates;
        this.minimalPoint = minimalPoint;
        this.description = description;
        this.difficulty = difficulty;
        this.author = author;
    }

    /**
     * Конструктор для загрузки объекта из файла (если нужно).
     */
    public LabWork(int id, String name, Coordinates coordinates, LocalDate creationDate,
                   Long minimalPoint, String description, Difficulty difficulty, Person author) {
        if (id <= 0)
            throw new IllegalArgumentException("id должен быть > 0");
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("name не может быть пустым");
        if (coordinates == null)
            throw new IllegalArgumentException("coordinates не могут быть null");
        if (creationDate == null)
            throw new IllegalArgumentException("creationDate не может быть null");
        if (minimalPoint == null || minimalPoint <= 0)
            throw new IllegalArgumentException("minimalPoint должен быть > 0");
        if (description == null || description.trim().isEmpty())
            throw new IllegalArgumentException("description не может быть пустым");
        if (author == null)
            throw new IllegalArgumentException("author не может быть null");

        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.minimalPoint = minimalPoint;
        this.description = description;
        this.difficulty = difficulty;
        this.author = author;

        // Обновляем счётчик id, чтобы не было конфликтов
        while (nextId.get() <= id) {
            nextId.incrementAndGet();
        }
    }

    // Геттеры
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public Long getMinimalPoint() {
        return minimalPoint;
    }
    public String getDescription() {
        return description;
    }
    public Difficulty getDifficulty() {
        return difficulty;
    }
    public Person getAuthor() {
        return author;
    }

    // Сеттеры (если нужно для update)
    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("name не может быть пустым");
        this.name = name;
    }
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null)
            throw new IllegalArgumentException("coordinates не могут быть null");
        this.coordinates = coordinates;
    }
    public void setMinimalPoint(Long minimalPoint) {
        if (minimalPoint == null || minimalPoint <= 0)
            throw new IllegalArgumentException("minimalPoint должен быть > 0");
        this.minimalPoint = minimalPoint;
    }
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty())
            throw new IllegalArgumentException("description не может быть пустым");
        this.description = description;
    }
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    public void setAuthor(Person author) {
        if (author == null)
            throw new IllegalArgumentException("author не может быть null");
        this.author = author;
    }

    @Override
    public int compareTo(LabWork other) {
        // Пример сортировки: сначала по minimalPoint, потом по id
        int cmp = this.minimalPoint.compareTo(other.minimalPoint);
        if (cmp == 0) {
            cmp = Integer.compare(this.id, other.id);
        }
        return cmp;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LabWork{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", minimalPoint=" + minimalPoint +
                ", description='" + description + '\'' +
                ", difficulty=" + difficulty +
                ", author=" + author +
                '}';
    }

    /** Для DAO: кто владелец записи (используем автор.name) */
    public String getOwnerLogin() {
        return author.getName();
    }
}
