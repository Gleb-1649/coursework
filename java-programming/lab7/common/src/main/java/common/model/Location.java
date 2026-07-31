package common.model;

import java.io.Serializable;

public class Location implements Serializable {
    private static final long serialVersionUID = 1L;
    private Double x;
    private int y;
    private float z;
    private String name;

    public Location(Double x, int y, float z, String name) {
        setX(x);
        setName(name);
        this.y = y;
        this.z = z;
    }

    public Double getX() { return x; }
    public void setX(Double x) {
        if (x == null) throw new IllegalArgumentException("x not null");
        this.x = x;
    }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public float getZ() { return z; }
    public void setZ(float z) { this.z = z; }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name not null/empty");
        this.name = name;
    }

    @Override
    public String toString() {
        return "Location{x=" + x + ", y=" + y + ", z=" + z + ", name='" + name + "'}";
    }
}