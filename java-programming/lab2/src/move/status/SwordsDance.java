package move.status;

import ru.ifmo.se.pokemon.*;

public class SwordsDance extends StatusMove {

    public SwordsDance(double power, double acc) {
        super(Type.NORMAL, power, acc);
    }

    @Override
    protected void applySelfEffects(Pokemon p){
        super.applySelfEffects(p);
        p.setMod(Stat.ATTACK, 2);
    }

    @Override
    protected String describe() {
        return "does Swords Dance";
    }
}
