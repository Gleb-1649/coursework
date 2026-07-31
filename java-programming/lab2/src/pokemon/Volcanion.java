package pokemon;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

import move.status.Rest;
import move.physical.BodySlam;
import move.physical.Bulldoze;
import move.special.SteamEruption;

public class Volcanion extends Pokemon {

    public Volcanion(String name, int level) {

        super(name, level);

        super.setType(Type.FIRE,Type.WATER);

        super.setStats(80,110,120,130,90,70);

        Rest res = new Rest(0,100);
        super.addMove(res);

        BodySlam bod = new BodySlam(85,100);
        super.addMove(bod);

        Bulldoze bul = new Bulldoze(60,100);
        super.addMove(bul);

        SteamEruption ste = new SteamEruption(110,95);
        super.addMove(ste);
    }
}
