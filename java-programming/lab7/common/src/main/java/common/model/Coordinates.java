package common.model;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long x;
    private Integer y;

    public Coordinates(Long x, Integer y) {
        setX(x);
        setY(y);
    }

    public Long getX() { return x; }
    public void setX(Long x) {
        if (x == null || x > 755) throw new IllegalArgumentException("x ≤ 755 and not null");
        this.x = x;
    }

    public Integer getY() { return y; }
    public void setY(Integer y) {
        if (y == null) throw new IllegalArgumentException("y not null");
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{x=" + x + ", y=" + y + '}';
    }
}
