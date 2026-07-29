package place.locations;
import place.enumeration.WorldTypeEnum;

public abstract class Arena extends AbstractPlace {
    private boolean bloody;
    private String type;

    public Arena(String placeName, WorldTypeEnum world, String legendaryLevel, boolean bloody, String type) {
        super(placeName, world, legendaryLevel);
        this.bloody = bloody;
        this.type = type;
    }

    public boolean isBloody() {
        return bloody;
    }

    public void setBloody(boolean bloody) {
        this.bloody = bloody;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract void startBattle();
}
