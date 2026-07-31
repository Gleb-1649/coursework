package move.status;

import ru.ifmo.se.pokemon.*;

public class Rest extends StatusMove {

    public Rest(double power, double acc) {
        super(Type.PSYCHIC, power, acc);
    }

    @Override
    protected void applySelfEffects(Pokemon p){
        super.applySelfEffects(p);
        int health = (int) Math.floor(p.getHP());
        Effect EffectObject = new Effect().turns(1).stat(Stat.HP, health - 100).condition(Status.SLEEP);
        p.addEffect(EffectObject);
    }

    @Override
    protected String describe() {
        return "does Rest";
    }
}
