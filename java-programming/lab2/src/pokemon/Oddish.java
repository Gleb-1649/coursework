package pokemon;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

import move.special.DazzlingGleam;
import move.status.SwordsDance;

public class Oddish extends Pokemon {

    public Oddish(String name, int level) {

        super(name, level);

        super.setType(Type.GRASS,Type.POISON);

        super.setStats(45,50,55,75,65,30);

        DazzlingGleam daz = new DazzlingGleam(80,100);
        super.addMove(daz);

        SwordsDance swo = new SwordsDance(0,100);
        super.addMove(swo);

    }
}
