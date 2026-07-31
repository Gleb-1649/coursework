package pokemon;

import ru.ifmo.se.pokemon.Type;

import move.status.PoisonPowder;

public class Vileplume extends Gloom {

    public Vileplume(String name, int level) {

        super(name, level);

        super.setType(Type.GRASS,Type.POISON);

        super.setStats(75,80,85,110,90,50);

        PoisonPowder poi = new PoisonPowder(0,75);
        super.addMove(poi);

    }
}