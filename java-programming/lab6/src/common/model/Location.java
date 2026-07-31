package common.model;

import java.io.Serializable;
import java.util.Objects;

public class Location implements Serializable {
    private static final long serialVersionUID = 1L;

    private Double x;
    private int y;
    private float z;
    private String name;

    public Location(Double x, int y, float z, String name) {
        if (x == null) {
            throw new IllegalArgumentException("x не может быть null");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name не может быть null или пустым");
        }
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }
    public Double getX() {
        return x;
    }
    public void setX(Double x) {
        if (x == null) {
            throw new IllegalArgumentException("x не может быть null");
        }
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public float getZ() {
        return z;
    }
    public void setZ(float z) {
        this.z = z;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("name не может быть null или пустым");
        }
        this.name = name;
    }
    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name='" + name + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return y == location.y &&
                Float.compare(location.z, z) == 0 &&
                Objects.equals(x, location.x) &&
                Objects.equals(name, location.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, name);
    }
}

