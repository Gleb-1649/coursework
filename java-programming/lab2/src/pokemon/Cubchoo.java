package pokemon;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

import move.special.IceBeam;
import move.special.IcyWind;
import move.physical.RockTomb;

public class Cubchoo extends Pokemon {

    public Cubchoo(String name, int level) {

        super(name, level);

        super.setType(Type.ICE);

        super.setStats(55,70,40,60,40,40);

        IceBeam ice = new IceBeam(90,100);
        super.addMove(ice);

        IcyWind icy = new IcyWind(55,95);
        super.addMove(icy);

        RockTomb roc = new RockTomb(60,95);
        super.addMove(roc);

    }
}