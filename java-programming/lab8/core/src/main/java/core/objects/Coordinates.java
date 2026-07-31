package core.objects;

import java.io.Serializable;

/**
 * Класс Coordinates – координаты LabWork.
 * Обязательно implements Serializable.
 */
public class Coordinates implements Serializable {
    private static final long serialVersionUID = 1L;

    private Double x; // x != null
    private Long y;   // y > -721

    public Coordinates(Double x, Long y) {
        if (x == null)
            throw new IllegalArgumentException("x не может быть null");
        if (y == null || y <= -721)
            throw new IllegalArgumentException("y должно быть > -721");
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }
    public Long getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" + "x=" + x + ", y=" + y + '}';
    }
}
