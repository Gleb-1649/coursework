package module;

import java.util.Objects;

/**
 * Класс Coordinates представляет координаты с полями x и y.
 * x не может быть null и должно быть ≤ 755, y не может быть null.
 */
public class Coordinates {
    private Long x;
    private Integer y;

    public Coordinates(Long x, Integer y) {
        if (x == null || x > 755) {
            throw new IllegalArgumentException("x не может быть null и должно быть ≤ 755");
        }
        if (y == null) {
            throw new IllegalArgumentException("y не может быть null");
        }
        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        if (x == null || x > 755) {
            throw new IllegalArgumentException("x не может быть null и должно быть ≤ 755");
        }
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        if (y == null) {
            throw new IllegalArgumentException("y не может быть null");
        }
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" + "x=" + x + ", y=" + y + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
