package move.special;

import ru.ifmo.se.pokemon.*;

public class IceBeam extends SpecialMove {

    public IceBeam(double power, double acc) {
        super(Type.ICE, power, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        Effect e = new Effect().chance(0.1).condition(Status.FREEZE).turns(1);
        p.setCondition(e);
    }

    @Override
    protected String describe() {

        return "does Ice Beam";
    }
}
