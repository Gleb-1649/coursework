package move.status;

import ru.ifmo.se.pokemon.*;

public class PoisonPowder extends StatusMove {

    public PoisonPowder(double power, double acc) {
        super(Type.POISON, power, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        Effect e = new Effect().chance(1).condition(Status.POISON).turns(1);
        p.setCondition(e);
    }

    @Override
    protected String describe() {
        return "does Poison Powder";
    }
}
