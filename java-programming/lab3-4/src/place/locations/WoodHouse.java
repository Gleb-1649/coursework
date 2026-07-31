package place.locations;

import place.enumeration.WorldTypeEnum;

public abstract class WoodHouse extends AbstractPlace {
    private boolean burn;

    public WoodHouse(String placeName, WorldTypeEnum world, String legendaryLevel, boolean burn) {
        super(placeName, world, legendaryLevel);
        this.burn = burn;
    }

    public boolean isBurn() {
        return burn;
    }

    public void setBurn(boolean burn) {
        this.burn = burn;
    }

    public abstract void creak();
    public abstract void fire();
}