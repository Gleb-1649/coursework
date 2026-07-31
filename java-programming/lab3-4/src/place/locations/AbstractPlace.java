package place.locations;
import place.Place;
import place.enumeration.WorldTypeEnum;

import java.util.Objects;

public abstract class AbstractPlace implements Place {
    private String placeName;
    private WorldTypeEnum world;
    private String legendaryLevel;

    public AbstractPlace(String placeName, WorldTypeEnum world, String legendaryLevel) {
        this.placeName = placeName;
        this.world = world;
        this.legendaryLevel = legendaryLevel;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public WorldTypeEnum getWorld() {
        return world;
    }

    public void setWorld(WorldTypeEnum world) {
        this.world = world;
    }

    public String getLegendaryLevel() {
        return legendaryLevel;
    }

    public void setLegendaryLevel(String legendaryLevel) {
        this.legendaryLevel = legendaryLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPlace that = (AbstractPlace) o;
        return Objects.equals(placeName, that.placeName) && world == that.world && Objects.equals(legendaryLevel, that.legendaryLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeName, world, legendaryLevel);
    }

    @Override
    public String toString() {
        return "AbstractPlace{" +
                "placeName='" + placeName + '\'' +
                ", world=" + world +
                ", legendaryLevel='" + legendaryLevel + '\'' +
                '}';
    }
}
