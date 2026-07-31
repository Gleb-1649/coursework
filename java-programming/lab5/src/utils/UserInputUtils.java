package utils;

import enumeration.Color;
import manager.CollectionManager;
import module.Person;
import module.Coordinates;
import module.Location;
import enumeration.Country;
import java.time.ZonedDateTime;
import java.util.Scanner;

/**
 * Класс UserInputUtils обеспечивает интерактивный ввод данных для создания объекта Person.
 */
public class UserInputUtils {
    private final Scanner scanner;
    private final Validator validator;
    private boolean silent = false;

    public UserInputUtils(CollectionManager collectionManager) {
        this.scanner = new Scanner(System.in);
        this.validator = new Validator();
    }

    public void setSilent(boolean silent) {
        this.silent = silent;
    }

    public Person readPerson() {
        try {
            if (!silent) System.out.println("Введите имя:");
            String name = validator.validateNonEmptyString(scanner.nextLine(), "Имя");

            if (!silent) System.out.println("Введите координаты x (Long, не null, ≤ 755):");
            Long x = validator.validateLong(scanner.nextLine(), "Координата x", 755L);

            if (!silent) System.out.println("Введите координаты y (Integer, не null):");
            Integer y = validator.validateInteger(scanner.nextLine(), "Координата y");
            Coordinates coordinates = new Coordinates(x, y);

            if (!silent) System.out.println("Введите рост (Float, > 0):");
            Float height = validator.validateFloat(scanner.nextLine(), "Рост", true);

            if (!silent) System.out.println("Введите вес (Float, > 0, можно оставить пустым):");
            String weightInput = scanner.nextLine().trim();
            Float weight = weightInput.isEmpty() ? null : validator.validateFloat(weightInput, "Вес", true);

            if (!silent) {
                System.out.println("Введите цвет глаз. Допустимые значения:");
                for (Color c : Color.values()) {
                    System.out.print(c.name() + " ");
                }
                System.out.println();
            }
            String eyeColorInput = scanner.nextLine().trim();
            Color eyeColor = eyeColorInput.isEmpty() ? null : Color.valueOf(eyeColorInput.toUpperCase());

            if (!silent) {
                System.out.println("Введите страну. Допустимые значения:");
                for (Country c : Country.values()) {
                    System.out.print(c.name() + " ");
                }
                System.out.println();
            }
            String nationalityInput = validator.validateNonEmptyString(scanner.nextLine(), "Страна");
            Country nationality = Country.valueOf(nationalityInput.toUpperCase());

            if (!silent) System.out.println("Введите данные местоположения:");
            if (!silent) System.out.println("Введите координату x (Double):");
            Double locX = Double.parseDouble(scanner.nextLine().trim());

            if (!silent) System.out.println("Введите координату y (int):");
            int locY = Integer.parseInt(scanner.nextLine().trim());

            if (!silent) System.out.println("Введите координату z (float):");
            float locZ = Float.parseFloat(scanner.nextLine().trim());

            if (!silent) System.out.println("Введите название места:");
            String locName = validator.validateNonEmptyString(scanner.nextLine(), "Название места");
            Location location = new Location(locX, locY, locZ, locName);

            return new Person("0", name, coordinates, ZonedDateTime.now(), height, weight, eyeColor, nationality, location);
        } catch (Exception e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
            return readPerson();
        }
    }
}
