package move.special;

import ru.ifmo.se.pokemon.*;

public class IcyWind extends SpecialMove {

    public IcyWind(double power, double acc) {
        super(Type.ICE, power, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        Effect e = new Effect().chance(1).stat(Stat.SPEED, -1).turns(1);
        p.setCondition(e);
    }

    @Override
    protected String describe() {
        return "does Ice Beam";
    }
}
