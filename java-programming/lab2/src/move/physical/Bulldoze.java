package move.physical;

import ru.ifmo.se.pokemon.*;

public class Bulldoze extends PhysicalMove {

    public Bulldoze(double power, double acc) {
        super(Type.GROUND, power, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        Effect EffectObject = new Effect().stat(Stat.SPEED, -1).turns(1);
        p.addEffect(EffectObject);
    }

    @Override
    protected String describe() {
        return "does Bulldoze";
    }
}