package object.box;

public abstract class AverageBoxes extends Boxes {
    private String contents;
    private String picture;

    public AverageBoxes(String type, double weight, String material, String contents, String picture) {
        super(type, weight, material);
        this.contents = contents;
        this.picture = picture;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public abstract void decay();
}
