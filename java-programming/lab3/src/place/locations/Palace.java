package place.locations;

import place.enumeration.WorldTypeEnum;

public abstract class Palace extends AbstractPlace {
    private boolean open;

    public Palace(String placeName, WorldTypeEnum world, String legendaryLevel, boolean open) {
        super(placeName, world, legendaryLevel);
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public abstract void secure();
}
