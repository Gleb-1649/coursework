package move.physical;

import ru.ifmo.se.pokemon.*;

public class RockTomb extends PhysicalMove {

    public RockTomb(double power, double acc) {
        super(Type.ROCK, power, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        Effect e = new Effect().chance(1).stat(Stat.SPEED, -1).turns(1);
        p.setCondition(e);
    }

    @Override
    protected String describe() {
        return "does Rock Tomb";
    }
}
