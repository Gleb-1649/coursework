package move.special;

import ru.ifmo.se.pokemon.*;

public class DazzlingGleam extends SpecialMove {

    public DazzlingGleam(double power, double acc) {
        super(Type.FAIRY, power, acc);
    }

    @Override
    protected String describe() {
        return "does Dazzling Gleam";
    }
}
