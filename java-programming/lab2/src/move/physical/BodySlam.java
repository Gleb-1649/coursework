package move.physical;

import ru.ifmo.se.pokemon.*;

public class BodySlam extends PhysicalMove {

    public BodySlam(double power, double acc) {
        super(Type.NORMAL, power, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        p.setCondition(
                new Effect().chance(0.3).condition(Status.PARALYZE).turns(1)
        );
    }

    @Override
    protected String describe() {
        return "does Body Slam";
    }
}