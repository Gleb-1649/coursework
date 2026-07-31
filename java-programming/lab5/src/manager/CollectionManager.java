package manager;

import module.Person;
import utils.XmlUtils;
import java.io.File;
import java.util.Collections;
import java.util.Vector;
import module.IdGenerator;

/**
 * Класс CollectionManager управляет коллекцией объектов Person.
 */
public class CollectionManager {
    private Vector<Person> collection;
    private final long initializationDate;
    private final String fileName;
    private final IdGenerator idGenerator;

    public CollectionManager(String fileName, XmlUtils xmlUtils) {
        this.fileName = fileName;
        File file = new File(fileName);
        if (file.exists()) {
            this.collection = xmlUtils.loadFromXml(fileName);
        } else {
            System.out.println("Файл не найден. Используется пустая коллекция.");
            this.collection = new Vector<>();
        }
        this.initializationDate = System.currentTimeMillis();
        this.idGenerator = new IdGenerator();
    }

    public String generateId() {
        return idGenerator.generateId();
    }

    public String getInfo() {
        return "Тип: Vector, Дата инициализации: " + initializationDate + ", Количество элементов: " + collection.size();
    }


    public void add(Person person) {
        person.setId(generateId());
        collection.add(person);
    }

    public void show() {
        StringBuilder sb = new StringBuilder();
        if (collection.isEmpty()) {
            System.out.println("Коллекция пуста.");
        }
        for (Person p : collection) {
            sb.append(p).append("\n");
        }
        System.out.print(sb.toString());
    }

    public void removeById(String id) {
        collection.removeIf(p -> p.getId().equals(id));
    }

    public void clear() {
        collection.clear();
    }

    public void save(XmlUtils xmlUtils) {
        xmlUtils.saveToXml(collection, fileName);
    }

    public void shuffle() {
        Collections.shuffle(collection);
    }

    public void sort() {
        Collections.sort(collection);
    }

    public void removeLower(Person element) {
        collection.removeIf(p -> p.compareTo(element) < 0);
    }

    public void countLessThanLocation(String locationStr) {
        long count = collection.stream()
                .filter(p -> p.getLocation() != null &&
                        p.getLocation().getName().compareTo(locationStr) < 0)
                .count();
        System.out.println("Количество: " + count);
    }

    public void printAscending() {
        Vector<Person> sorted = new Vector<>(collection);
        Collections.sort(sorted);
        sorted.forEach(System.out::println);
    }

    public void printUniqueEyeColor() {
        collection.stream()
                .map(Person::getEyeColor)
                .distinct()
                .forEach(System.out::println);
    }

    public void update(String id, Person person) {
        boolean found = false;
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getId().equals(id)) {
                person.setId(id);
                collection.set(i, person);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Элемент с id " + id + " не найден.");
        }
    }

    public Vector<Person> getCollection() {
        return collection;
    }
}

