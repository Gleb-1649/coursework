package object.box;
import object.Object;

import java.util.Objects;

public abstract class Boxes implements Object {
    private String type;
    private double weight;
    private String material;

    public Boxes(String type, double weight, String material) {
        this.type = type;
        this.weight = weight;
        this.material = material;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public abstract void openBox();

    @Override
    public void lie() {
        System.out.println("ящик лежит на земле.");
    }

    @Override
    public void take() {
        System.out.println("ящик подобран");
    }

    @Override
    public void overlook() {
        System.out.println("осмотр ящика");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boxes boxes = (Boxes) o;
        return Double.compare(boxes.weight, weight) == 0 &&
                Objects.equals(type, boxes.type) &&
                Objects.equals(material, boxes.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, weight, material);
    }

    @Override
    public String toString() {
        return "Boxes{" +
                "type='" + type + '\'' +
                ", weight=" + weight +
                ", material='" + material + '\'' +
                '}';
    }
}
