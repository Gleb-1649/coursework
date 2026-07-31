package move.status;

import ru.ifmo.se.pokemon.*;

public class SweetScent extends StatusMove {

    public SweetScent(double power, double acc) {
        super(Type.NORMAL, power, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        p.setMod(Stat.EVASION, -1);
    }

    @Override
    protected String describe(){
        return "does Sweet Scent";
    }
}
