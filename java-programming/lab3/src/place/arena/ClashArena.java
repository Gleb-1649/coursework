package place.arena;

import place.locations.Arena;
import place.enumeration.WorldTypeEnum;

public class ClashArena extends Arena {
    public ClashArena(String placeName, WorldTypeEnum world, String legendaryLevel, boolean bloody, String type) {
        super(placeName, world, legendaryLevel, bloody, type);
    }

    @Override
    public void startBattle() {
        System.out.println("Битва начинается в " + getPlaceName());
    }

    @Override
    public void exist() {
        System.out.println(getPlaceName() + " существвует в " + getWorld());
    }

    @Override
    public void visit() {
        System.out.println("Визит the Clash Arena...");
    }
}
