package pokemon;

import ru.ifmo.se.pokemon.Type;

import move.status.SweetScent;

public class Gloom extends Oddish {

    public Gloom(String name, int level) {

        super(name, level);

        super.setType(Type.GRASS,Type.POISON);

        super.setStats(60,65,70,85,75,40);

        SweetScent swe = new SweetScent(0,100);
        super.addMove(swe);

    }
}
