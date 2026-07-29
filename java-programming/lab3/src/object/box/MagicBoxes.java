package object.box;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// Abstract Class MagicBoxes
public abstract class MagicBoxes extends Boxes {
    private boolean flicker;

    public MagicBoxes(String type, double weight, String material, boolean flicker) {
        super(type, weight, material);
        this.flicker = flicker;
    }

    protected ArrayList<String> Color  = new ArrayList<>(Arrays.asList("Red",
            "Blue",
            "Yellow",
            "Pink",
            "Black"
    ));

    public boolean isFlicker() {
        return flicker;
    }

    public void setFlicker(boolean flicker) {
        this.flicker = flicker;
    }

    public abstract void teleport();

    public abstract void explode();

    protected int getRandomColor(){
        Random random = new Random();
        int index = random.nextInt(6);
        return index-1;
    }

    public String getColor(){
        return Color.get(getRandomColor());
    }
}



