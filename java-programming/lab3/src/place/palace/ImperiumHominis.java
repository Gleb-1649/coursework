package place.palace;

import place.locations.Palace;
import place.enumeration.WorldTypeEnum;

public class ImperiumHominis extends Palace {
    public ImperiumHominis(String placeName, WorldTypeEnum world, String legendaryLevel, boolean open) {
        super(placeName, world, legendaryLevel, open);
    }

    public void wellSecure() {
        System.out.println(getPlaceName() + " хорошо охраняем.");
    }

    @Override
    public void secure() {
        System.out.println(getPlaceName() + " обеспечивается охрана...");
    }

    @Override
    public void exist() {
        System.out.println(getPlaceName() + "существует в " + getWorld());
    }

    @Override
    public void visit() {
        System.out.println("Визит " + getPlaceName());
    }
}
