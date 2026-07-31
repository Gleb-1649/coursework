package pokemon;

import ru.ifmo.se.pokemon.Type;

import move.physical.Bulldoze;

public class Beartic extends Cubchoo {

    public Beartic(String name, int level) {

        super(name, level);

        super.setType(Type.ICE);

        super.setStats(95,130,80,70,80,50);

        super.addMove(
                new Bulldoze(60,100)
        );
    }
}
