package move.special;

import ru.ifmo.se.pokemon.*;

public class SteamEruption extends SpecialMove {

    public SteamEruption(double power, double acc) {
        super(Type.WATER, power, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        Effect e = new Effect().chance(0.3).condition(Status.BURN).turns(1);
        p.setCondition(e);
    }

    @Override
    protected String describe() {
        return "does Steam Eruption";
    }
}
